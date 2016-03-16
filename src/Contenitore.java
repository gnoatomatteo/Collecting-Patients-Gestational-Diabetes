import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Matteo on 10/03/2016.
 */
public class Contenitore {
    private Vector<Paziente> db;

    /*CONTENITORE*/
    public Contenitore(){
        db=new Vector<>();
    }

    /*METODI AGGIUNTA E RIMOZIONE*/
    public void addPaziente(Paziente p){
        p.setNumeroPaziente(db.size()+1);
        db.add(p);
    }
    public void removePaziente(Paziente p){
        boolean trovato=false;
        for(Iterator<Paziente> it= db.iterator(); it.hasNext() && !trovato; it.next()){
            if(p.getNumeroPaziente() == it.next().getNumeroPaziente()){
                it.remove();
                trovato=true;
            }
        }
    }
    /*METODI STATISTICI*/
    public int numeroPazienti(){
        return db.size();
    }

    /*LOADER*/
    public void load(){
        try {
            File config = new File("db.xml");
            DocumentBuilderFactory file = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = file.newDocumentBuilder();
            Document document= builder.parse(config);
            document.getDocumentElement().normalize();
            NodeList pazienti= document.getElementsByTagName("paziente");

            for(int i=0; i<pazienti.getLength(); i++){
               Node nodo= pazienti.item(i);
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element paziente = (Element)nodo;

                    /*COSTRUZIONE CAMPI OBBLIGATORI*/
                    // nome e cognome
                    String nome= paziente.getElementsByTagName("nome").item(0).getFirstChild().getNodeValue();
                    String cognome= paziente.getElementsByTagName("cognome").item(0).getFirstChild().getNodeValue();

                    //data di nascita
                    NodeList data = paziente.getElementsByTagName("dataDiNascita");
                    Node dataNodo = data.item(0);
                    Element dataDiNascita= (Element)dataNodo;
                    String giornoString= dataDiNascita.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                    String meseString= dataDiNascita.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                    String annoString= dataDiNascita.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                    int giorno = Integer.parseInt(giornoString);
                    int mese = Integer.parseInt(meseString+1);
                    int anno = Integer.parseInt(annoString);
                    GregorianCalendar ddn= new GregorianCalendar(giorno,mese,anno);

                    //nazionalità
                    String naz= paziente.getElementsByTagName("nazionalita").item(0).getFirstChild().getNodeValue();

                    //peso inizio gravidanza
                    String pesoInizioString= paziente.getElementsByTagName("pesoInizioGravidanza").item(0).getFirstChild().getNodeValue();
                    float pig= Float.parseFloat(pesoInizioString);

                    //tipologia diabete
                    String tipologiaDiabete = paziente.getElementsByTagName("tipologiaDiabete").item(0).getFirstChild().getNodeValue();

                    /*COSTRUZIONE OGGETTO*/
                    Paziente aux= new Paziente(nome,cognome,ddn,naz,pig,tipologiaDiabete);

                    /*COMPLETAMENTO CAMPI*/
                    //telefono
                    NodeList telefonoNodo= paziente.getElementsByTagName("telefono").item(0).getChildNodes();
                    Node nValue = (Node)telefonoNodo.item(0);
                    if(!(nValue == null)) {
                        String telefono = paziente.getElementsByTagName("telefono").item(0).getFirstChild().getNodeValue();
                        aux.setTelefono(telefono);
                    }

                    //parità
                    NodeList paritaNodo = paziente.getElementsByTagName("parita").item(0).getChildNodes();
                    nValue = (Node)paritaNodo.item(0);
                    if(!(nValue == null)){
                        String parita = paziente.getElementsByTagName("parita").item(0).getFirstChild().getNodeValue();
                        aux.setParita(parita);
                    }

                    //data ultima mestruazione
                    data = paziente.getElementsByTagName("dataUltimaMestruazione");
                    dataNodo = data.item(0);
                    dataDiNascita= (Element)dataNodo;
                    NodeList giornoNodo = dataDiNascita.getElementsByTagName("giorno").item(0).getChildNodes();
                    nValue = (Node)giornoNodo.item(0);
                    if(!(nValue == null)) {
                        giornoString = dataDiNascita.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                        meseString = dataDiNascita.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                        annoString = dataDiNascita.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                        giorno = Integer.parseInt(giornoString);
                        mese = Integer.parseInt(meseString);
                        anno = Integer.parseInt(annoString);
                        GregorianCalendar dum = new GregorianCalendar(giorno, mese, anno);
                        aux.setUltimaMestruazione(dum);
                    }

                    //peso fine gravidanza
                    NodeList pesoFineNodo = paziente.getElementsByTagName("pesoFineGravidanza").item(0).getChildNodes();
                    nValue = (Node)pesoFineNodo.item(0);
                    if(!(nValue == null)){
                        String pesoFineString = paziente.getElementsByTagName("pesoFineGravidanza").item(0).getFirstChild().getNodeValue();
                        float pfg = Float.parseFloat(pesoFineString);
                        aux.setPesoFineGravidanza(pfg);
                    }

                    //alimentazione preconcezionale
                    NodeList alimentazione = paziente.getElementsByTagName("alimentazionePreconcezionale");
                    Node alimentazioneNodo = alimentazione.item(0);
                    Element alimentazionePreconcezionale = (Element) alimentazioneNodo;
                        //elemento carbroidrati
                        NodeList carboidratiNodo = alimentazionePreconcezionale.getElementsByTagName("carboidrati").item(0).getChildNodes();
                        nValue = (Node) carboidratiNodo.item(0);
                        if(!(nValue == null)){
                            String carboidrati = alimentazionePreconcezionale.getElementsByTagName("carboidrati").item(0).getFirstChild().getNodeValue();
                            String carboidratiComplessi = alimentazionePreconcezionale.getElementsByTagName("carboidratiComplessi").item(0).getFirstChild().getNodeValue();
                            String proteine = alimentazionePreconcezionale.getElementsByTagName("proteine").item(0).getFirstChild().getNodeValue();
                            String grassi = alimentazionePreconcezionale.getElementsByTagName("grassi").item(0).getFirstChild().getNodeValue();
                            aux.setAlimentazionePreconcezionale(carboidrati, carboidratiComplessi, proteine, grassi);
                        }

                    //terapia
                    NodeList terapiaNodo = paziente.getElementsByTagName("terapia").item(0).getChildNodes();
                    nValue = (Node)terapiaNodo.item(0);
                    if(!(nValue == null)){
                        String terapia = paziente.getElementsByTagName("terapia").item(0).getFirstChild().getNodeValue();
                        aux.setTerapia(terapia);
                    }

                    //dietaSeguita
                    NodeList dietaNodo = paziente.getElementsByTagName("dietaSeguita").item(0).getChildNodes();
                    nValue = (Node)dietaNodo.item(0);
                    if(!(nValue == null)){
                        String dieta = paziente.getElementsByTagName("dietaSeguita").item(0).getFirstChild().getNodeValue();
                        boolean dietaBool = Boolean.parseBoolean(dieta);
                        aux.setDietaSeguita(dietaBool);
                    }

                    //modalità parto
                    NodeList modalitaPartoNodo= paziente.getElementsByTagName("modalitaParto").item(0).getChildNodes();
                    nValue = (Node)modalitaPartoNodo.item(0);
                    if(!(nValue == null)){
                        String modalitaParto = paziente.getElementsByTagName("modalitaParto").item(0).getFirstChild().getNodeValue();
                        aux.setModalitaParto(modalitaParto);
                    }

                    //peso del bambino
                    NodeList pesoBambinoNodo= paziente.getElementsByTagName("pesoBambino").item(0).getChildNodes();
                    nValue = (Node)pesoBambinoNodo.item(0);
                    if(!(nValue == null)){
                        String pesoBambinoString = paziente.getElementsByTagName("pesoBambino").item(0).getFirstChild().getNodeValue();
                        int pesoBambino = Integer.parseInt(pesoBambinoString);
                        aux.setPesoBambino(pesoBambino);
                    }

                    //emoglobina Glicata
                    NodeList emoglobinaGlicataNodo = paziente.getElementsByTagName("emoglobinaGlicata").item(0).getChildNodes();
                    nValue = (Node)emoglobinaGlicataNodo.item(0);
                    if(!(nValue == null)){
                        String emoglobinaGlicataString = paziente.getElementsByTagName("emoglobinaGlicata").item(0).getFirstChild().getNodeValue();
                        int emoglobinaGlicata = Integer.parseInt(emoglobinaGlicataString);
                        aux.setEmoglobinaGlicata(emoglobinaGlicata);
                    }

                    //ecografiaTerzoTrimestre
                    NodeList ecografiaTerzoTrimestre = paziente.getElementsByTagName("ecografiaTerzoTrimestre");
                    Node ecografiaTerzoTrimestreNodo = ecografiaTerzoTrimestre.item(0);
                    Element ecografiaTerzoTrimestreElement = (Element)ecografiaTerzoTrimestreNodo;
                    NodeList DPBNode = ecografiaTerzoTrimestreElement.getElementsByTagName("DPB").item(0).getChildNodes();
                    nValue = (Node) DPBNode.item(0);
                    if(!(nValue == null)){
                        String DPBString = ecografiaTerzoTrimestreElement.getElementsByTagName("DPB").item(0).getFirstChild().getNodeValue();
                        String CCString = ecografiaTerzoTrimestreElement.getElementsByTagName("CC").item(0).getFirstChild().getNodeValue();
                        String CAString = ecografiaTerzoTrimestreElement.getElementsByTagName("CA").item(0).getFirstChild().getNodeValue();
                        String EFWString = ecografiaTerzoTrimestreElement.getElementsByTagName("EFW").item(0).getFirstChild().getNodeValue();
                        String LA = ecografiaTerzoTrimestreElement.getElementsByTagName("LA").item(0).getFirstChild().getNodeValue();
                            //recupero data
                            data = ecografiaTerzoTrimestreElement.getElementsByTagName("dataEsecuzione").item(0).getChildNodes();
                            dataNodo = data.item(0);
                            Element dataEsecuzioneElement = (Element)dataNodo;
                            giornoString = dataEsecuzioneElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                            meseString = dataEsecuzioneElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                            annoString = dataEsecuzioneElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                            giorno = Integer.parseInt(giornoString);
                            mese = Integer.parseInt(meseString);
                            anno = Integer.parseInt(annoString);
                            GregorianCalendar dataEsecuzione = new GregorianCalendar(giorno,mese,anno);
                        float dpb = Float.parseFloat(DPBString);
                        float cc = Float.parseFloat(CCString);
                        float ca = Float.parseFloat(CAString);
                        int efw = Integer.parseInt(EFWString);
                        aux.setEcografiaTerzoTrimestre(dpb,cc,ca,efw,LA,dataEsecuzione);
                    }

                    //ecografiaOstetrica
                    NodeList ecografiaOstetrica = paziente.getElementsByTagName("ecografiaOstetrica");
                    Node ecografiaOstetricaNodo = ecografiaTerzoTrimestre.item(0);
                    Element ecografiaOstetricaElement = (Element)ecografiaOstetricaNodo;
                    DPBNode = ecografiaTerzoTrimestreElement.getElementsByTagName("DPB").item(0).getChildNodes();
                    nValue = (Node) DPBNode.item(0);
                    if(!(nValue == null)){
                        String DPBString = ecografiaTerzoTrimestreElement.getElementsByTagName("DPB").item(0).getFirstChild().getNodeValue();
                        String CCString = ecografiaTerzoTrimestreElement.getElementsByTagName("CC").item(0).getFirstChild().getNodeValue();
                        String CAString = ecografiaTerzoTrimestreElement.getElementsByTagName("CA").item(0).getFirstChild().getNodeValue();
                        String EFWString = ecografiaTerzoTrimestreElement.getElementsByTagName("EFW").item(0).getFirstChild().getNodeValue();
                        String LA = ecografiaTerzoTrimestreElement.getElementsByTagName("LA").item(0).getFirstChild().getNodeValue();
                        //recupero data
                        data = ecografiaTerzoTrimestreElement.getElementsByTagName("dataEsecuzione").item(0).getChildNodes();
                        dataNodo = data.item(0);
                        Element dataEsecuzioneElement = (Element)dataNodo;
                        giornoString = dataEsecuzioneElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                        meseString = dataEsecuzioneElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                        annoString = dataEsecuzioneElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                        giorno = Integer.parseInt(giornoString);
                        mese = Integer.parseInt(meseString);
                        anno = Integer.parseInt(annoString);
                        GregorianCalendar dataEsecuzione = new GregorianCalendar(giorno,mese,anno);
                        float dpb = Float.parseFloat(DPBString);
                        float cc = Float.parseFloat(CCString);
                        float ca = Float.parseFloat(CAString);
                        int efw = Integer.parseInt(EFWString);
                        aux.setEcografiaOstetrica(dpb, cc, ca, efw, LA, dataEsecuzione);
                    }

                    //glicemiaNeonato
                    NodeList glicemiaNeonato = paziente.getElementsByTagName("glicemiaNeonato").item(0).getChildNodes();
                    nValue = (Node) glicemiaNeonato.item(0);
                    if(!(nValue == null)){
                        String glicemiaNeonatoString = paziente.getElementsByTagName("glicemiaNeonato").item(0).getFirstChild().getNodeValue();
                        float glicemiaNeonatoFloat = Float.parseFloat(glicemiaNeonatoString);
                        aux.setGlicemiaNeonato(glicemiaNeonatoFloat);
                    }

                    //note personali
                    NodeList notePersonaliNodo= paziente.getElementsByTagName("notePersonali").item(0).getChildNodes();
                    nValue = (Node)notePersonaliNodo.item(0);
                    if(!(nValue == null)){
                        String notePersonali = paziente.getElementsByTagName("notePersonali").item(0).getFirstChild().getNodeValue();
                        aux.setNotePersonali(notePersonali);
                    }

                    //data inserimento
                    data = paziente.getElementsByTagName("dataInserimento");
                    dataNodo = data.item(0);
                    Element dataInserimentoElement= (Element)dataNodo;
                    giornoNodo= dataInserimentoElement.getElementsByTagName("giorno").item(0).getChildNodes();
                    nValue = (Node)giornoNodo.item(0);
                    if(!(nValue == null)) {
                        giornoString = dataInserimentoElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                        meseString = dataInserimentoElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                        annoString = dataInserimentoElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                        giorno = Integer.parseInt(giornoString);
                        mese = Integer.parseInt(meseString);
                        anno = Integer.parseInt(annoString);
                        GregorianCalendar dataInserimento = new GregorianCalendar(giorno, mese, anno);
                        aux.setDataInserimento(dataInserimento);
                    }

                    //numero paziente
                    String numPaziente = paziente.getElementsByTagName("numeroPaziente").item(0).getFirstChild().getNodeValue();
                    int numero= Integer.parseInt(numPaziente);
                    aux.setNumeroPaziente(numero);

                    /*MEMORIZZAZIONE OGGETTO NEL DB*/
                    db.add(aux);
                }

            }


        } catch (ParserConfigurationException p){
            System.out.println("errore nella configurazione del parser");
        } catch (SAXException s){
            System.out.println("errore nel parser del file");
        } catch (IOException i){
            System.out.println("errore I/O");
        }
    }

    /*SAVIOR*/
    public void save(){
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("database");
            doc.appendChild(rootElement);

            /*CREO ELEMENTI PAZIENTE*/
            for(Iterator<Paziente> iteratore = db.iterator(); iteratore.hasNext();){
                Paziente it = iteratore.next();

                //elemento paziente
                Element paziente = doc.createElement("paziente");
                rootElement.appendChild(paziente);

                // elemento nome paziente
                Element nome = doc.createElement("nome");
                nome.setTextContent(it.getNome());
                paziente.appendChild(nome);

                //elemento cognome
                Element cognome = doc.createElement("cognome");
                cognome.setTextContent(it.getCognome());
                paziente.appendChild(cognome);

                //elemento data di nascita
                Element dataDiNascita = doc.createElement("dataDiNascita");
                paziente.appendChild(dataDiNascita);

                    //elemento giorno di dataDiNascita
                    Element giornoDataDiNascita = doc.createElement("giorno");
                    Integer giornoInteger = it.getDataDiNascita().get(GregorianCalendar.DATE);
                    giornoDataDiNascita.setTextContent(giornoInteger.toString());
                    dataDiNascita.appendChild(giornoDataDiNascita);

                    //elemento mese di dataDiNascita
                    Element meseDataDiNascita = doc.createElement("mese");
                    Integer meseInteger = it.getDataDiNascita().get(GregorianCalendar.MONTH);
                    meseDataDiNascita.setTextContent(meseInteger.toString());
                    dataDiNascita.appendChild(meseDataDiNascita);

                    //elemento anno di dataDiNascita
                    Element annoDataDiNascita = doc.createElement("anno");
                    Integer annoInteger = it.getDataDiNascita().get(GregorianCalendar.YEAR);
                    annoDataDiNascita.setTextContent(annoInteger.toString());
                    dataDiNascita.appendChild(annoDataDiNascita);

                //elemento telefono
                Element telefono = doc.createElement("telefono");
                if(it.getTelefono() != null ){
                    telefono.setTextContent(it.getTelefono());
                }
                paziente.appendChild(telefono);

                //elemento nazionalita
                Element nazionalita = doc.createElement("nazionalita");
                if(it.getNazionalita() != null){
                    nazionalita.setTextContent(it.getNazionalita());
                }
                paziente.appendChild(nazionalita);

                //elemento parita
                Element parita = doc.createElement("parita");
                if(it.getParita() != null){
                    parita.setTextContent(it.getParita());
                }
                paziente.appendChild(parita);

                //elemento dataUltimaMestruazione
                Element dataUltimaMestruazione = doc.createElement("dataUltimaMestruazione");
                paziente.appendChild(dataUltimaMestruazione);
                    //elemento giorno di dataUltimaMestruazione
                    Element giornoDataUltimaMestruazione = doc.createElement("giorno");
                    //elemento mese di dataUltimaMestruazione
                    Element meseDataUltimaMestruazione = doc.createElement("mese");
                    //elemento anno di dataUltimaMestruazione
                    Element annoDataUltimaMestruazione = doc.createElement("anno");

                    if(it.getUltimaMestruazione() != null){
                        giornoInteger = it.getUltimaMestruazione().get(GregorianCalendar.DATE);
                        giornoDataUltimaMestruazione.setTextContent(giornoInteger.toString());
                        meseInteger = it.getUltimaMestruazione().get(GregorianCalendar.MONTH);
                        meseDataUltimaMestruazione.setTextContent(meseInteger.toString());
                        annoInteger = it.getUltimaMestruazione().get(GregorianCalendar.YEAR);
                        annoDataUltimaMestruazione.setTextContent(annoInteger.toString());
                    }

                    dataUltimaMestruazione.appendChild(giornoDataUltimaMestruazione);
                    dataUltimaMestruazione.appendChild(meseDataUltimaMestruazione);
                    dataUltimaMestruazione.appendChild(annoDataUltimaMestruazione);

                //elemento pesoInizioGravidanza
                Element pesoInizioGravidanza = doc.createElement("pesoInizioGravidanza");
                Float pesoInizioGravidanzaFloat = new Float(it.getPesoInizioGravidanza());
                pesoInizioGravidanza.setTextContent(pesoInizioGravidanzaFloat.toString());
                paziente.appendChild(pesoInizioGravidanza);

                //elemento pesoFineGravidanza
                Element pesoFineGravidanza = doc.createElement("pesoFineGravidanza");
                if(it.getPesoFineGravidanza() != 0){
                    Float pesoFineGravidanzaFloat = new Float(it.getPesoFineGravidanza());
                    pesoFineGravidanza.setTextContent(pesoFineGravidanzaFloat.toString());
                }
                paziente.appendChild(pesoFineGravidanza);

                //elemento alimentaizonePreconcezionale
                Element alimentazionePreconcezionale = doc.createElement("alimentazionePreconcezionale");
                paziente.appendChild(alimentazionePreconcezionale);
                    //elementi di alimentazionePreconcezionale
                    Element carboidratiAlimentazionePreconcezionale = doc.createElement("carboidrati");
                    Element carboidratiComplessiAlimentazionePreconcezionale = doc.createElement("carboidratiComplessi");
                    Element proteineAlimentazionePreconcezionale = doc.createElement("proteine");
                    Element grassiAlimentazionePreconcezionale = doc.createElement("grassi");
                    if(it.getAlimentazionePreconcezionale() != null){
                        carboidratiAlimentazionePreconcezionale.setTextContent(it.getAlimentazionePreconcezionale().carbroidati);
                        carboidratiComplessiAlimentazionePreconcezionale.setTextContent(it.getAlimentazionePreconcezionale().carbroidatiComplessi);
                        proteineAlimentazionePreconcezionale.setTextContent(it.getAlimentazionePreconcezionale().proteine);
                        grassiAlimentazionePreconcezionale.setTextContent(it.getAlimentazionePreconcezionale().grassi);
                    }
                    alimentazionePreconcezionale.appendChild(carboidratiAlimentazionePreconcezionale);
                    alimentazionePreconcezionale.appendChild(carboidratiComplessiAlimentazionePreconcezionale);
                    alimentazionePreconcezionale.appendChild(proteineAlimentazionePreconcezionale);
                    alimentazionePreconcezionale.appendChild(grassiAlimentazionePreconcezionale);

                //elemento terapia
                Element terapia = doc.createElement("terapia");
                if(it.getTerapia() != null){
                    terapia.setTextContent(it.getTerapia());
                }
                paziente.appendChild(terapia);

                //elemento dietaSeguita
                Element dietaSeguita = doc.createElement("dietaSeguita");
                Boolean dietaSeguitaBoolean = new Boolean(it.getDietaSeguita());
                dietaSeguita.setTextContent(dietaSeguitaBoolean.toString());
                paziente.appendChild(dietaSeguita);

                //elemento tipologiaDiabete
                Element tipologiaDiabete = doc.createElement("tipologiaDiabete");
                tipologiaDiabete.setTextContent(it.getTipologiaDiabete());
                paziente.appendChild(tipologiaDiabete);

                //elemento modalitaParto
                Element modalitaParto = doc.createElement("modalitaParto");
                if(it.getModalitaParto() != null){
                    modalitaParto.setTextContent(it.getModalitaParto());
                }
                paziente.appendChild(modalitaParto);

                //elemento pesoBambino
                Element pesoBambino = doc.createElement("pesoBambino");
                if(it.getPesoBambino() != 0){
                    Integer pesoBambinoInteger = new Integer(it.getPesoBambino());
                    pesoBambino.setTextContent(pesoBambinoInteger.toString());
                }
                paziente.appendChild(pesoBambino);

                //elemento emoglobinaGlicata
                Element emoglobinaGlicata = doc.createElement("emoglobinaGlicata");
                if(it.getEmoglobinaGlicata() != 0){
                    Integer emoglobinaGlicataInteger = new Integer(it.getEmoglobinaGlicata());
                    emoglobinaGlicata.setTextContent(emoglobinaGlicataInteger.toString());
                }
                paziente.appendChild(emoglobinaGlicata);

                //elemento ecografiaTerzoTrimestre
                Element ecografiaTerzoTrimestre = doc.createElement("ecografiaTerzoTrimestre");
                Element DPB = doc.createElement("DPB");
                Element CC = doc.createElement("CC");
                Element CA = doc.createElement("CA");
                Element EFW = doc.createElement("EFW");
                Element LA = doc.createElement("LA");
                Element dataEsecuzione = doc.createElement("dataEsecuzione");
                ecografiaTerzoTrimestre.appendChild(DPB);
                ecografiaTerzoTrimestre.appendChild(CC);
                ecografiaTerzoTrimestre.appendChild(CA);
                ecografiaTerzoTrimestre.appendChild(EFW);
                ecografiaTerzoTrimestre.appendChild(LA);
                ecografiaTerzoTrimestre.appendChild(dataEsecuzione);
                Element giorno = doc.createElement("giorno");
                Element mese = doc.createElement("mese");
                Element anno = doc.createElement("anno");
                dataEsecuzione.appendChild(giorno);
                dataEsecuzione.appendChild(mese);
                dataEsecuzione.appendChild(anno);
                if(it.getEcografiaTerzoTrimestre() != null){
                    Float DPBFloat = it.getEcografiaTerzoTrimestre().DBP;
                    DPB.setTextContent(DPBFloat.toString());
                    Float CCFloat = it.getEcografiaTerzoTrimestre().CC;
                    CC.setTextContent(CCFloat.toString());
                    Float CAFloat = it.getEcografiaTerzoTrimestre().CA;
                    CA.setTextContent(CAFloat.toString());
                    Integer EFWInteger = it.getEcografiaTerzoTrimestre().EFW;
                    EFW.setTextContent(EFWInteger.toString());
                    LA.setTextContent(it.getEcografiaTerzoTrimestre().LA);
                    giornoInteger = it.getEcografiaTerzoTrimestre().dataEsecuzione.get(GregorianCalendar.DATE);
                    meseInteger = it.getEcografiaTerzoTrimestre().dataEsecuzione.get(GregorianCalendar.MONTH) + 1;
                    annoInteger = it.getEcografiaTerzoTrimestre().dataEsecuzione.get(GregorianCalendar.YEAR);
                    giorno.setTextContent(giornoInteger.toString());
                    mese.setTextContent(meseInteger.toString());
                    anno.setTextContent(annoInteger.toString());
                }
                paziente.appendChild(ecografiaTerzoTrimestre);

                //elemento ecografiaOstetrica
                Element ecografiaOstetrica = doc.createElement("ecografiaOstetrica");
                DPB = doc.createElement("DPB");
                CC = doc.createElement("CC");
                CA = doc.createElement("CA");
                EFW = doc.createElement("EFW");
                LA = doc.createElement("LA");
                dataEsecuzione = doc.createElement("dataEsecuzione");
                ecografiaOstetrica.appendChild(DPB);
                ecografiaOstetrica.appendChild(CC);
                ecografiaOstetrica.appendChild(CA);
                ecografiaOstetrica.appendChild(EFW);
                ecografiaOstetrica.appendChild(LA);
                ecografiaOstetrica.appendChild(dataEsecuzione);
                giorno = doc.createElement("giorno");
                mese = doc.createElement("mese");
                anno = doc.createElement("anno");
                dataEsecuzione.appendChild(giorno);
                dataEsecuzione.appendChild(mese);
                dataEsecuzione.appendChild(anno);
                if(it.getEcografiaOstetrica() != null){
                    Float DPBFloat = it.getEcografiaOstetrica().DBP;
                    DPB.setTextContent(DPBFloat.toString());
                    Float CCFloat = it.getEcografiaOstetrica().CC;
                    CC.setTextContent(CCFloat.toString());
                    Float CAFloat = it.getEcografiaOstetrica().CA;
                    CA.setTextContent(CAFloat.toString());
                    Integer EFWInteger = it.getEcografiaOstetrica().EFW;
                    EFW.setTextContent(EFWInteger.toString());
                    LA.setTextContent(it.getEcografiaOstetrica().LA);
                    giornoInteger = it.getEcografiaOstetrica().dataEsecuzione.get(GregorianCalendar.DATE);
                    meseInteger = it.getEcografiaOstetrica().dataEsecuzione.get(GregorianCalendar.MONTH) + 1;
                    annoInteger = it.getEcografiaOstetrica().dataEsecuzione.get(GregorianCalendar.YEAR);
                    giorno.setTextContent(giornoInteger.toString());
                    mese.setTextContent(meseInteger.toString());
                    anno.setTextContent(annoInteger.toString());
                }
                paziente.appendChild(ecografiaOstetrica);

                //elemento glicemiaNeonato
                Element glicemiaNeonato = doc.createElement("glicemiaNeonato");
                if(it.getGlicemiaNeonato() != 0){
                    Float glicemiaNeonatoFloat = it.getGlicemiaNeonato();
                    glicemiaNeonato.setTextContent(glicemiaNeonatoFloat.toString());
                }
                paziente.appendChild(glicemiaNeonato);

                //elemento notePersonali
                Element notePersonali = doc.createElement("notePersonali");
                if(it.getNotePersonali() != null){
                    notePersonali.setTextContent(it.getNotePersonali());
                }
                paziente.appendChild(notePersonali);

                //elemento dataInserimento
                Element dataInserimento = doc.createElement("dataInserimento");
                paziente.appendChild(dataInserimento);
                    //elemento giorno di dataInserimento
                    Element giornoDataInserimento = doc.createElement("giorno");
                    giornoInteger = it.getDataInserimento().get(GregorianCalendar.DATE);
                    giornoDataInserimento.setTextContent(giornoInteger.toString());
                    dataInserimento.appendChild(giornoDataInserimento);
                    //elemento mese di dataInserimento
                    Element meseDataInserimento = doc.createElement("mese");
                    meseInteger = it.getDataInserimento().get(GregorianCalendar.MONTH);
                    meseDataInserimento.setTextContent(meseInteger.toString());
                    dataInserimento.appendChild(meseDataInserimento);
                    //elemento anno di dataInserimento
                    Element annoDataInserimento = doc.createElement("anno");
                    annoInteger = it.getDataInserimento().get(GregorianCalendar.YEAR);
                    annoDataInserimento.setTextContent(annoInteger.toString());
                    dataInserimento.appendChild(annoDataInserimento);

                //elemento numeroPaziente
                Element numeroPaziente = doc.createElement("numeroPaziente");
                Integer numeroPazienteInteger = new Integer(it.getNumeroPaziente());
                numeroPaziente.setTextContent(numeroPazienteInteger.toString());
                paziente.appendChild(numeroPaziente);
            }

            //scrittura della struttura creata nel file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("db.xml"));
            transformer.transform(source, result);



        } catch (ParserConfigurationException p){
            System.out.printf("errore lettura del file");
        } catch (TransformerConfigurationException t){
            System.out.println("errore nella configurazione del file");
        } catch (TransformerException e){
            System.out.printf("errore nella trasformazione del file");
        }
    }
}
