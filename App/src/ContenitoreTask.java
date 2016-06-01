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
public class ContenitoreTask {
    public Vector<Task> taskDB; // contiene i task attivi
    public Vector<Task> taskSvolti; // contiene i task svolti

    /*COSTRUTTORE*/
    public ContenitoreTask(){
        taskDB = new Vector<>();
        taskSvolti = new Vector<>();
    }

    /*METODI ADD*/
    public void addTask(Task t){
        t.setNumeroTask(taskDB.size()+taskSvolti.size()+1);
        taskDB.add(t);
        save();
    }

    public void terminaTask(Task t){
        boolean trovato=false;
        for(Iterator<Task> it= taskDB.iterator(); it.hasNext() && !trovato;) {
            Task aux = it.next();
            if (aux.equals(t)){
                aux.setSvolto(true);
                trovato = true;
            }
        }
        save();
    }
    public void riattivaTask(Task t){
        boolean trovato=false;
        for(Iterator<Task> it= taskSvolti.iterator(); it.hasNext() && !trovato;) {
            Task aux = it.next();
            if (it.equals(t)){
                aux.setSvolto(false);
                trovato=true;
            }
        }
        save();

    }

    /*LOADER*/
    public void load(){
        System.out.println("ContenitroeTask.load()");
        try{
            DocumentBuilderFactory file = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = file.newDocumentBuilder();
            Document document= builder.parse(new File("taskDB.xml"));
            NodeList tasks= document.getElementsByTagName("task");

            for(int i=0; i<tasks.getLength(); i++){
                Node nodo = tasks.item(i);
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                    Element task = (Element)nodo;

                    /*COSTRUZIONE CAMPI DATI*/
                    //titolo
                    String titolo = task.getElementsByTagName("titolo").item(0).getFirstChild().getNodeValue();

                    //contenuto
                    String content= task.getElementsByTagName("contenuto").item(0).getFirstChild().getNodeValue();

                    // tipo task
                    String tipo = task.getElementsByTagName("tipoTask").item(0).getFirstChild().getNodeValue();

                    //importanza
                    String importanza = task.getElementsByTagName("importanza").item(0).getFirstChild().getNodeValue();

                    //data impostata per terminazione
                    NodeList dataImpostataTermineNodo = task.getElementsByTagName("dataImpostataTermine");
                    Node dataNodo = dataImpostataTermineNodo.item(0);
                    Element dataImpostataTermineElement= (Element)dataNodo;
                    String giornoString= dataImpostataTermineElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                    String meseString= dataImpostataTermineElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                    String annoString= dataImpostataTermineElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                    Integer giorno = new Integer(giornoString);
                    Integer mese = new Integer(meseString);
                    Integer anno = new Integer(annoString);
                    GregorianCalendar dataImpostataTermine= new GregorianCalendar(anno,mese,giorno);

                    /*COSTRUZIONE OGGETTO*/
                    Task aux= new Task(titolo, content, tipo, importanza, dataImpostataTermine);

                    // numero task
                    String numeroString = task.getElementsByTagName("numeroTask").item(0).getFirstChild().getNodeValue();
                    int numero = Integer.parseInt(numeroString);
                    aux.setNumeroTask(numero);

                    //data creazione
                    NodeList dataCreazioneNodo = task.getElementsByTagName("dataCreazione");
                    dataNodo = dataCreazioneNodo.item(0);
                    Element dataCreazioneElement= (Element)dataNodo;
                    giornoString= dataCreazioneElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                    meseString= dataCreazioneElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                    annoString= dataCreazioneElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                    giorno = new Integer(giornoString);
                    mese = new Integer(meseString);
                    anno = new Integer(annoString);
                    GregorianCalendar dataCreazione= new GregorianCalendar(anno,mese,giorno);
                    aux.setDataCreazione(dataCreazione);

                    // svolto
                    String svoltoString = task.getElementsByTagName("svolto").item(0).getFirstChild().getNodeValue();
                    boolean svolto = Boolean.parseBoolean(svoltoString);
                    aux.setSvolto(svolto);

                    //dataTerminazione
                    NodeList dataTerminazioneNodo = task.getElementsByTagName("dataTerminazione");
                    dataNodo = dataTerminazioneNodo.item(0);
                    Element dataTerminazioneElement= (Element)dataNodo;
                    NodeList giornoNodo= dataTerminazioneElement.getElementsByTagName("giorno").item(0).getChildNodes();
                    Node nValue = (Node)giornoNodo.item(0);
                    if(!(nValue == null)) {
                        giornoString = dataTerminazioneElement.getElementsByTagName("giorno").item(0).getFirstChild().getNodeValue();
                        meseString = dataTerminazioneElement.getElementsByTagName("mese").item(0).getFirstChild().getNodeValue();
                        annoString = dataTerminazioneElement.getElementsByTagName("anno").item(0).getFirstChild().getNodeValue();
                        giorno = new Integer(giornoString);
                        mese = new Integer(meseString);
                        anno = new Integer(annoString);
                        GregorianCalendar dataTerminazione = new GregorianCalendar(anno,mese,giorno);
                        aux.setDataTerminazione(dataTerminazione);
                    }

                    /*INSERIMENTO TASK NELL'APPOSITO VETTORE*/
                    if(aux.getSvolto()){
                        taskSvolti.add(aux);
                    }
                    else{
                        taskDB.add(aux);
                    }

                }
            }

        } catch (IOException e){
            System.out.printf("errore di I/O");
        } catch (ParserConfigurationException p ){
            System.out.println("errore nella lettura del file");
        } catch (SAXException s){
            System.out.println("SAXException");
        }
    }

    /*SAVIOR*/
    public void save(){
        System.out.println("ContenitoreTask.save()");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("database");
            doc.appendChild(rootElement);




            /*CREO ELEMENTI DI TASK ATTIVI*/
            for(Iterator<Task> iteratore = taskDB.iterator(); iteratore.hasNext() ;) {
                Task it = iteratore.next();
                //elemento task
                Element task = doc.createElement("task");
                rootElement.appendChild(task);

                //elemento numeroTask
                Element numeroTask = doc.createElement("numeroTask");
                Integer numeroTaskInteger = new Integer(it.getNumeroTask());
                numeroTask.setTextContent(numeroTaskInteger.toString());
                task.appendChild(numeroTask);

                //elemento titolo
                Element titolo = doc.createElement("titolo");
                titolo.appendChild(doc.createTextNode(it.getTitolo()));
                task.appendChild(titolo);

                //elemento contenuto
                Element contenuto = doc.createElement("contenuto");
                contenuto.setTextContent(it.getContenuto());
                task.appendChild(contenuto);

                //elemento tipoTask
                Element tipoTask = doc.createElement("tipoTask");
                tipoTask.setTextContent(it.getTipoTask());
                task.appendChild(tipoTask);

                //elemento importanza
                Element importanza = doc.createElement("importanza");
                importanza.setTextContent(it.getImportanza());
                task.appendChild(importanza);

                //elemento dataCreazione
                Element dataCreazione = doc.createElement("dataCreazione");
                task.appendChild(dataCreazione);

                    //elemento giorno di data creazione
                    Element giornoDataCreazione = doc.createElement("giorno");
                    Integer giornoInteger= new Integer(it.getDataCreazione().get(GregorianCalendar.DATE));
                    giornoDataCreazione.setTextContent(giornoInteger.toString());
                    dataCreazione.appendChild(giornoDataCreazione);

                    //elemento mese di data creazione
                    Element meseDataCreazione = doc.createElement("mese");
                    Integer meseInteger= new Integer(it.getDataCreazione().get(GregorianCalendar.MONTH)+1);
                    meseDataCreazione.setTextContent(meseInteger.toString());
                    dataCreazione.appendChild(meseDataCreazione);

                    //elemento anno di data creazione
                    Element annoDataCreazione = doc.createElement("anno");
                    Integer annoInteger= new Integer(it.getDataCreazione().get(GregorianCalendar.YEAR));
                    annoDataCreazione.setTextContent(annoInteger.toString());
                    dataCreazione.appendChild(annoDataCreazione);

                //elemento dataImpostataTermine
                Element dataImpostataTermine = doc.createElement("dataImpostataTermine");
                task.appendChild(dataImpostataTermine);

                    //elemento giorno di dataImpostataTermine
                    Element giornoDataImpostataTermine = doc.createElement("giorno");
                    giornoInteger= new Integer(it.getDataImpostataTermine().get(GregorianCalendar.DATE));
                    giornoDataImpostataTermine.setTextContent(giornoInteger.toString());
                    dataImpostataTermine.appendChild(giornoDataImpostataTermine);

                    //elemento mese di dataImpostataTermine
                    Element meseDataImpostataTermine = doc.createElement("mese");
                    meseInteger= new Integer(it.getDataImpostataTermine().get(GregorianCalendar.MONTH)+1);
                    meseDataImpostataTermine.setTextContent(meseInteger.toString());
                    dataImpostataTermine.appendChild(meseDataImpostataTermine);

                    //elemento anno di dataImpostataTermine
                    Element annoDataImpostataTermine = doc.createElement("anno");
                    annoInteger= new Integer(it.getDataCreazione().get(GregorianCalendar.YEAR));
                    annoDataImpostataTermine.setTextContent(annoInteger.toString());
                    dataImpostataTermine.appendChild(annoDataImpostataTermine);

                //elemento svolto
                Element svolto = doc.createElement("svolto");
                Boolean svoltoBoolean = new Boolean(it.getSvolto());
                svolto.setTextContent(svoltoBoolean.toString());
                task.appendChild(svolto);

                //elemento dataTerminazione
                Element dataTerminazione = doc.createElement("dataTerminazione");
                task.appendChild(dataTerminazione);

                //elemento giorno di data terminazione
                Element giornoDataTerminazione = doc.createElement("giorno");

                //elemento mese di data terminazione
                Element meseDataTerminazione = doc.createElement("mese");

                //elemento anno di data terminazione
                Element annoDataTerminazione = doc.createElement("anno");

                if(it.isTerminated()){
                    giornoInteger = new Integer(it.getDataTerminazione().get(GregorianCalendar.DATE));
                    meseInteger = new Integer(it.getDataTerminazione().get(GregorianCalendar.MONTH));
                    annoInteger = new Integer(it.getDataTerminazione().get(GregorianCalendar.YEAR));
                    giornoDataTerminazione.setTextContent(giornoInteger.toString());
                    meseDataTerminazione.setTextContent(meseInteger.toString());
                    annoDataTerminazione.setTextContent(annoInteger.toString());
                }

                dataTerminazione.appendChild(giornoDataTerminazione);
                dataTerminazione.appendChild(meseDataTerminazione);
                dataTerminazione.appendChild(annoDataTerminazione);

            }
            /*CREO ELEMENTI DI TASK SVOLTI*/
            for(Iterator<Task> iteratoreSvolti = taskSvolti.iterator(); iteratoreSvolti.hasNext() ;) {
                Task it = iteratoreSvolti.next();

                //elemento task
                Element task = doc.createElement("task");
                rootElement.appendChild(task);

                //elemento numeroTask
                Element numeroTask = doc.createElement("numeroTask");
                Integer numeroTaskInteger = new Integer(it.getNumeroTask());
                numeroTask.setTextContent(numeroTaskInteger.toString());
                task.appendChild(numeroTask);

                //elemento titolo
                Element titolo = doc.createElement("titolo");
                titolo.setTextContent(it.getTitolo());
                task.appendChild(titolo);

                //elemento contenuto
                Element contenuto = doc.createElement("contenuto");
                contenuto.setTextContent(it.getContenuto());
                task.appendChild(contenuto);

                //elemento tipoTask
                Element tipoTask = doc.createElement("tipoTask");
                tipoTask.setTextContent(it.getTipoTask());
                task.appendChild(tipoTask);

                //elemento importanza
                Element importanza = doc.createElement("importanza");
                importanza.setTextContent(it.getImportanza());
                task.appendChild(importanza);

                //elemento dataCreazione
                Element dataCreazione = doc.createElement("datacreazione");
                task.appendChild(dataCreazione);

                //elemento giorno di data creazione
                Element giornoDataCreazione = doc.createElement("giorno");
                Integer giornoInteger= new Integer(it.getDataCreazione().getGregorianChange().getDay());
                giornoDataCreazione.setTextContent(giornoInteger.toString());
                dataCreazione.appendChild(giornoDataCreazione);

                //elemento mese di data creazione
                Element meseDataCreazione = doc.createElement("mese");
                Integer meseInteger= new Integer(it.getDataCreazione().getGregorianChange().getMonth());
                giornoDataCreazione.setTextContent(meseInteger.toString());
                dataCreazione.appendChild(meseDataCreazione);

                //elemento anno di data creazione
                Element annoDataCreazione = doc.createElement("anno");
                Integer annoInteger= new Integer(it.getDataCreazione().getGregorianChange().getYear());
                giornoDataCreazione.setTextContent(annoInteger.toString());
                dataCreazione.appendChild(annoDataCreazione);

                //elemento svolto
                Element svolto = doc.createElement("svolto");
                Boolean svoltoBoolean = new Boolean(it.getSvolto());
                svolto.setTextContent(svoltoBoolean.toString());
                task.appendChild(svolto);

                //elemento dataTerminazione
                Element dataTerminazione = doc.createElement("dataTerminazione");
                task.appendChild(dataTerminazione);

                //elemento giorno di data terminazione
                Element giornoDataTerminazione = doc.createElement("giorno");

                //elemento mese di data terminazione
                Element meseDataTerminazione = doc.createElement("mese");

                //elemento anno di data terminazione
                Element annoDataTerminazione = doc.createElement("anno");

                if(it.isTerminated()){
                    giornoInteger = new Integer(it.getDataTerminazione().getGregorianChange().getDay());
                    meseInteger = new Integer(it.getDataTerminazione().getGregorianChange().getMonth());
                    annoInteger = new Integer(it.getDataTerminazione().getGregorianChange().getYear());
                    giornoDataTerminazione.setTextContent(giornoInteger.toString());
                    meseDataTerminazione.setTextContent(meseInteger.toString());
                    annoDataTerminazione.setTextContent(annoInteger.toString());
                }

                dataTerminazione.appendChild(giornoDataTerminazione);
                dataTerminazione.appendChild(meseDataTerminazione);
                dataTerminazione.appendChild(annoDataTerminazione);

            }

            //scrittura della struttura creata nel file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("taskDB.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException p){
            System.out.println("errore nella lettura del file");
        } catch (TransformerConfigurationException t){
            System.out.println("errore nella scrittura del file");
        } catch (TransformerException r){
            System.out.println("errore di trasformazione, che non ho idea di cosa voglia dire");
        }
    }


}
