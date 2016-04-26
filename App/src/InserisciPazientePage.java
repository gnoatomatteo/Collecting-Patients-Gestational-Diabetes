import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 24/04/2016.
 */

public class InserisciPazientePage extends JFrame {
    // campi dati propri di una paziente
    private JButton inserisci;
    private JButton annulla;
    private String nome;
    private String cognome;
    private GregorianCalendar dataDiNascita;
    private String telefono; // non obbligatorio
    private String nazionalita;
    private String parita; // non obbligatorio
    private GregorianCalendar ultimaMestruazione;
    private float pesoInizioGravidanza;
    private float pesoFineGravidanza; // non obbligatorio
    private String terapia; // non obbligatorio
    private int emoglobinaGlicata; // non obbligatorio
    private boolean dietaSeguita; // non obbligatorio
    private String tipologiaDiabete;
    private String modalitaParto; // non obbligatorio
    private int pesoBambino; // non obbligatorio
    private String notePersonali; // non obbligatorio
    private float glicemiaNeonato;

    // campi dati per costruire sotto-oggetti
    //Alimentazione, attenzione: questa classe non ha un costruttore, ma i campi sono inizializzati da metodi della classe Paziente
    private String carboidrati;
    private String carboidratiComplessi;
    private String proteine;
    private String grassi;

    //Ecografia
    private float DBP;
    private float CC;
    private float CA;
    private int EFW;
    private String LA; // enum("scarso", "regolare", "abbondante")
    private GregorianCalendar dataEsecuzione;

    /* COSTRUTTORE DI PAZIENTE:
    * nome, cognome, dataDiNascita, nazionalita, pesiInizioGravidanza, tipologiaDiabete
    * */

    public InserisciPazientePage(Contenitore pazientiDB){
        super("Inserisci nuova paziente");
        super.setPreferredSize(new Dimension(800,600));
        setSize(new Dimension(800,600));
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel nomeLabel = new JLabel("Nome*:");
        nomeLabel.setBounds(10,10,150,25);
        mainPanel.add(nomeLabel);

        final JTextField nomeText = new JTextField();
        nomeText.setBounds(170,10,150,25);
        mainPanel.add(nomeText);

        JLabel cognomeLabel = new JLabel("Cognome*:");
        cognomeLabel.setBounds(10,40,150,25);
        mainPanel.add(cognomeLabel);

        final JTextField cognomeText = new JTextField();
        cognomeText.setBounds(170,40,150,25);
        mainPanel.add(cognomeText);

        JLabel dataNascitaLabel = new JLabel("Data di Nascita*:");
        dataNascitaLabel.setBounds(10,70,150,25);
        mainPanel.add(dataNascitaLabel);

        String[] giornoSelect = new String[31];
        for(int i=0; i < 31 ; ++i){
            Integer giornoInteger = new Integer(i+1);
            giornoSelect[i] = giornoInteger.toString();
        }

        final JComboBox<String> giornoText = new JComboBox<>(giornoSelect);
        giornoText.setBounds(170,70,85,25);
        mainPanel.add(giornoText);

        String[] meseSelect = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        final JComboBox<String> meseText = new JComboBox<>(meseSelect);
        meseText.setBounds(260,70,85,25);
        mainPanel.add(meseText);

        String[] annoSelect = new String[58];
        int annoAux = 1960;
        for(int i=0; i < 58 ; ++i){
            annoSelect[i] = new Integer(annoAux).toString();
            if(annoAux == 2017){
                break;
            }
            annoAux++;
        }
        final JComboBox<String> annoText = new JComboBox<>(annoSelect);
        annoText.setBounds(350,70,85,25);
        mainPanel.add(annoText);

        JLabel telefonoLabel = new JLabel("Telefono:");
        telefonoLabel.setBounds(10,100,150,25);
        mainPanel.add(telefonoLabel);

        final JTextField telefonoText = new JTextField();
        telefonoText.setBounds(170,100,150,25);
        mainPanel.add(telefonoText);

        JLabel nazionalitaLabel = new JLabel("nazionalita'*:");
        nazionalitaLabel.setBounds(10,130,150,25);
        mainPanel.add(nazionalitaLabel);

        final JTextField nazionalitaText = new JTextField();
        nazionalitaText.setBounds(170,130,150,25);
        mainPanel.add(nazionalitaText);

        JLabel paritaLabel = new JLabel("Parita':");
        paritaLabel.setBounds(10,160,150,25);
        mainPanel.add(paritaLabel);

        final JTextField paritaText = new JTextField();
        paritaText.setBounds(170,160,150,25);
        mainPanel.add(paritaText);

        JLabel dataUltimaMestruzione = new JLabel("Data ultima mestruazione*:");
        dataUltimaMestruzione.setBounds(10,190,150,25);
        mainPanel.add(dataUltimaMestruzione);

        final JComboBox<String> giornoText2 = new JComboBox<>(giornoSelect);
        giornoText2.setBounds(170,190,85,25);
        mainPanel.add(giornoText2);

        final JComboBox<String> meseText2 = new JComboBox<>(meseSelect);
        meseText2.setBounds(260,190,85,25);
        mainPanel.add(meseText2);

        final JComboBox<String> annoText2 = new JComboBox<>(annoSelect);
        annoText2.setBounds(350,190,85,25);
        mainPanel.add(annoText2);

        JLabel pesoInizioGravidanzaLabel = new JLabel("Peso inizio gravidanza*:");
        pesoInizioGravidanzaLabel.setBounds(10,220,150,25);
        mainPanel.add(pesoInizioGravidanzaLabel);

        final JTextField pesoInizioGravidanzaText = new JTextField();
        pesoInizioGravidanzaText.setBounds(170,220,70,25);
        mainPanel.add(pesoInizioGravidanzaText);
        JLabel kilogrammi = new JLabel("Kg");
        kilogrammi.setBounds(245,220,20,25);
        mainPanel.add(kilogrammi);

        JLabel pesoFineGravidanzaLabel = new JLabel("Peso fine gravidanza:");
        pesoFineGravidanzaLabel.setBounds(10,250,150,25);
        mainPanel.add(pesoFineGravidanzaLabel);

        final JTextField pesoFineGravidanzaText = new JTextField();
        pesoFineGravidanzaText.setBounds(170,250,70,25);
        mainPanel.add(pesoFineGravidanzaText);
        JLabel kilogrammi_2 = new JLabel("Kg");
        kilogrammi_2.setBounds(245,250,20,25);
        mainPanel.add(kilogrammi_2);

        JLabel terapiaLabel = new JLabel("Terapia:");
        terapiaLabel.setBounds(10,280,85,25);
        mainPanel.add(terapiaLabel);

        String[] terapiaSelect = {"non specificato","insulinica", "dieta"};
        final JComboBox<String> terapiatext = new JComboBox<>(terapiaSelect);
        terapiatext.setBounds(170,280,150,25);
        mainPanel.add(terapiatext);

        JLabel emoglobinaGlicataLabel = new JLabel("Emoglobina Glicata:");
        emoglobinaGlicataLabel.setBounds(10,310,150,25);
        mainPanel.add(emoglobinaGlicataLabel);

        final JTextField emoglobinaGlicataText = new JTextField();
        emoglobinaGlicataText.setBounds(170,310,150,25);
        mainPanel.add(emoglobinaGlicataText);

        JLabel dietaSeguitaLabel = new JLabel("Dieta seguita*:");
        dietaSeguitaLabel.setBounds(10,340,150,25);
        mainPanel.add(dietaSeguitaLabel);

        String[] dietaSeguitaSelect = {"si", "no"};
        final JComboBox<String> dietaSeguitaText = new JComboBox<>(dietaSeguitaSelect);
        dietaSeguitaText.setBounds(170,340,85,25);
        mainPanel.add(dietaSeguitaText);

        JLabel tipologiaDiabete = new JLabel("Tipologia diabete*:");
        tipologiaDiabete.setBounds(10,370,150,25);
        mainPanel.add(tipologiaDiabete);

        String[] tipologiaDiabeteSelect = {"gestazionale","pregestazionale"};
        final JComboBox<String> tipologiaDiabeteText = new JComboBox<>(tipologiaDiabeteSelect);
        tipologiaDiabeteText.setBounds(170,370,150,25);
        mainPanel.add(tipologiaDiabeteText);

        JLabel modalitaPartoLabel = new JLabel("Modalita' parto:");
        modalitaPartoLabel.setBounds(10,400,150,25);
        mainPanel.add(modalitaPartoLabel);

        String[] modalitaPartoSelect = {"non specificato","spontaneo", "indotto", "operativo", "taglio cesareo"};
        final JComboBox<String>  modalitaPartoText = new JComboBox<>(modalitaPartoSelect);
        modalitaPartoText.setBounds(170, 400, 150, 25);
        mainPanel.add(modalitaPartoText);

        JLabel pesoBambinoLabel = new JLabel("Peso del Neonato:");
        pesoBambinoLabel.setBounds(10,430,150,25);
        mainPanel.add(pesoBambinoLabel);

        final JTextField pesoBambinoText = new JTextField();
        pesoBambinoText.setBounds(170,430,70,25);
        mainPanel.add(pesoBambinoText);
        JLabel grammi = new JLabel("gr");
        grammi.setBounds(245,430,20,25);
        mainPanel.add(grammi);

        JLabel glicemiaNeonatoLabel = new JLabel("Glicemia neonato:");
        glicemiaNeonatoLabel.setBounds(10,460,150,25);
        mainPanel.add(glicemiaNeonatoLabel);

        final JTextField glicemiaNeonatoText = new JTextField();
        glicemiaNeonatoText.setBounds(170,460,70,25);
        mainPanel.add(glicemiaNeonatoText);

        JLabel notePersonaliLabel = new JLabel("Note personali");
        notePersonaliLabel.setBounds(10,490,150,25);
        mainPanel.add(notePersonaliLabel);

        final JTextArea notePersonaliText = new JTextArea();
        notePersonaliText.setBounds(170,490,275,75);
        mainPanel.add(notePersonaliText);

        // ALIMENTAZIONE:
            // meno di 2 alla settimana
            // dalle 3 alle 5
            // più di 6

        JLabel alimentazioneLabel = new JLabel("ALIMENTAZIONE SETTIMANALE:");
        alimentazioneLabel.setBounds(460,10,200,25);
        mainPanel.add(alimentazioneLabel);

        String[] alimentazioneSelect = {"meno di 2", "dalle 3 alle 5", "piu' di 6"};

        JLabel carboidratiLabel = new JLabel("Carboidrati:");
        carboidratiLabel.setBounds(460,40,150,25);
        mainPanel.add(carboidratiLabel);

        final JComboBox<String> carboidratiText = new JComboBox<>(alimentazioneSelect);
        carboidratiText.setBounds(600,40,150,25);
        mainPanel.add(carboidratiText);

        JLabel carboidratiComplessiLabel = new JLabel("Carboidrati complessi:");
        carboidratiComplessiLabel.setBounds(460, 70, 150, 25);
        mainPanel.add(carboidratiComplessiLabel);

        final JComboBox<String> carboidratiComplessiText = new JComboBox<>(alimentazioneSelect);
        carboidratiComplessiText.setBounds(600, 70, 150, 25);
        mainPanel.add(carboidratiComplessiText);

        JLabel proteineLabel = new JLabel("Proteine:");
        proteineLabel.setBounds(460,100,150,25);
        mainPanel.add(proteineLabel);

        final JComboBox<String> proteineText = new JComboBox<>(alimentazioneSelect);
        proteineText.setBounds(600,100,150,25);
        mainPanel.add(proteineText);

        JLabel grassiLabel = new JLabel("Grassi:");
        grassiLabel.setBounds(460,130,150,25);
        mainPanel.add(grassiLabel);

        final JComboBox<String> grassiText = new JComboBox<>(alimentazioneSelect);
        grassiText.setBounds(600,130,150,25);
        mainPanel.add(grassiText);

        JLabel volteAsettimanaLabel = new JLabel("volte a settimana");
        volteAsettimanaLabel.setBounds(600, 150, 150, 25);
        mainPanel.add(volteAsettimanaLabel);

        // ECOGRAFIA TERZO TRIMESTRE

        JLabel ecografiaLabel = new JLabel("ECOGRAFIA TERZO TRIMESTRE:");
        ecografiaLabel.setBounds(460,180,250,25);
        mainPanel.add(ecografiaLabel);

        JLabel dbpLabel = new JLabel("DBP:");
        dbpLabel.setBounds(460,210,40,25);
        mainPanel.add(dbpLabel);

        final JTextField dbpText = new JTextField();
        dbpText.setBounds(500,210,70,25);
        mainPanel.add(dbpText);

        JLabel ccLabel = new JLabel("CC:");
        ccLabel.setBounds(460,240,40,25);
        mainPanel.add(ccLabel);

        final JTextField ccText = new JTextField();
        ccText.setBounds(500,240,70,25);
        mainPanel.add(ccText);

        JLabel caLabel = new JLabel("CA:");
        caLabel.setBounds(460,270,40,25);
        mainPanel.add(caLabel);

        final JTextField caText = new JTextField();
        caText.setBounds(500,270,70,25);
        mainPanel.add(caText);

        JLabel efwLabel = new JLabel("EFW:");
        efwLabel.setBounds(460,300,40,25);
        mainPanel.add(efwLabel);

        final JTextField efwText = new JTextField();
        efwText.setBounds(500,300,70,25);
        mainPanel.add(efwText);

        JLabel laLabel = new JLabel("LA:");
        laLabel.setBounds(600, 210, 40, 25);
        mainPanel.add(laLabel);

        String[] laSelect = {"non definito", "regolare", "scarso", "abbondante"};
        final JComboBox<String> laText = new JComboBox<>(laSelect);
        laText.setBounds(630,210,120,25);
        mainPanel.add(laText);

        JLabel dataTerzoTrimestreLabel = new JLabel("Data:");
        dataTerzoTrimestreLabel.setBounds(600,240, 40, 25);
        mainPanel.add(dataTerzoTrimestreLabel);

        final JComboBox<String> giornoText3 = new JComboBox<>(giornoSelect);
        giornoText3.setBounds(630,240,85,25);
        mainPanel.add(giornoText3);

        final JComboBox<String> meseText3 = new JComboBox<>(meseSelect);
        meseText3.setBounds(630,270,85,25);
        mainPanel.add(meseText3);

        final JComboBox<String> annoText3 = new JComboBox<>(annoSelect);
        annoText3.setBounds(630,300,85,25);
        mainPanel.add(annoText3);


        // ECOGRAFIA OSTETRICA
        JLabel ecografiaOstetricaLabel = new JLabel("ECOGRAFIA OSTETRICA:");
        ecografiaOstetricaLabel.setBounds(460,330,200,25);
        mainPanel.add(ecografiaOstetricaLabel);

        JLabel dbpLabel2 = new JLabel("DBP:");
        dbpLabel2.setBounds(460,360,40,25);
        mainPanel.add(dbpLabel2);

        final JTextField dbpText2 = new JTextField();
        dbpText2.setBounds(500,360,70,25);
        mainPanel.add(dbpText2);

        JLabel ccLabel2 = new JLabel("CC:");
        ccLabel2.setBounds(460,390,40,25);
        mainPanel.add(ccLabel2);

        final JTextField ccText2 = new JTextField();
        ccText2.setBounds(500,390,70,25);
        mainPanel.add(ccText2);

        JLabel caLabel2 = new JLabel("CA:");
        caLabel2.setBounds(460,420,40,25);
        mainPanel.add(caLabel2);

        final JTextField caText2 = new JTextField();
        caText2.setBounds(500,420,70,25);
        mainPanel.add(caText2);

        JLabel efwLabel2 = new JLabel("EFW:");
        efwLabel2.setBounds(460,450,40,25);
        mainPanel.add(efwLabel2);

        final JTextField efwText2 = new JTextField();
        efwText2.setBounds(500,450,70,25);
        mainPanel.add(efwText2);

        JLabel laLabel2 = new JLabel("LA:");
        laLabel2.setBounds(600, 360, 40, 25);
        mainPanel.add(laLabel2);

        final JComboBox<String> laText2 = new JComboBox<>(laSelect);
        laText2.setBounds(630, 360,120,25);
        mainPanel.add(laText2);

        JLabel dataTerzoTrimestreLabel2 = new JLabel("Data:");
        dataTerzoTrimestreLabel2.setBounds(600,390, 40, 25);
        mainPanel.add(dataTerzoTrimestreLabel2);

        final JComboBox<String> giornoText4 = new JComboBox<>(giornoSelect);
        giornoText4.setBounds(630,390,85,25);
        mainPanel.add(giornoText4);

        final JComboBox<String> meseText4 = new JComboBox<>(meseSelect);
        meseText4.setBounds(630,420,85,25);
        mainPanel.add(meseText4);

        final JComboBox<String> annoText4 = new JComboBox<>(annoSelect);
        annoText4.setBounds(630,450,85,25);
        mainPanel.add(annoText4);

        inserisci = new JButton("Inserisci");
        inserisci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // controllare i campi obbligatori
                if(!(nomeText.getText().isEmpty() && cognomeText.getText().isEmpty() && nazionalitaText.getText().isEmpty() && pesoInizioGravidanzaText.getText().isEmpty())){
                    // costruisco la paziente
                    Integer giornoInteger = new Integer(giornoText.getSelectedItem().toString());
                    Integer meseInteger = new Integer(numeroMese(meseText.getSelectedItem().toString()));
                    Integer annoInteger = new Integer(annoText.getSelectedItem().toString());
                    GregorianCalendar dataNascita = new GregorianCalendar(annoInteger, meseInteger, giornoInteger);
                    Float pesoInizioGravidanza = new Float(pesoInizioGravidanzaText.toString());
                    Paziente nuovo = new Paziente(nomeText.getText(), cognomeText.getText(), dataNascita, nazionalitaText.getText(), pesoInizioGravidanza.floatValue(), tipologiaDiabeteText.getSelectedItem().toString());
                    // controllo campi non obbligatori:
                    if(telefonoText.getText() != null){
                        nuovo.setTelefono(telefonoText.getText());
                    }
                    if(paritaText.getText() != null){
                        nuovo.setParita(paritaText.getText());
                    }
                    giornoInteger = new Integer(giornoText2.getSelectedItem().toString());
                    meseInteger = new Integer(numeroMese(meseText2.getSelectedItem().toString()));
                    annoInteger = new Integer(annoText2.getSelectedItem().toString());
                    GregorianCalendar dataUltimaMestruzione = new GregorianCalendar(annoInteger,meseInteger,giornoInteger);
                    nuovo.setUltimaMestruazione(dataUltimaMestruzione);
                    if(pesoFineGravidanzaText.getText() != null){
                        Float pesoFineGravodanza = new Float(pesoFineGravidanzaText.getText());
                        nuovo.setPesoFineGravidanza(pesoFineGravodanza);
                    }
                    nuovo.setTerapia(terapiatext.getSelectedItem().toString());
                    if(emoglobinaGlicataText.getText() != null){
                        Integer emoglobinaGlicata = new Integer(emoglobinaGlicataText.getText());
                        nuovo.setEmoglobinaGlicata(emoglobinaGlicata);
                    }
                    if(dietaSeguitaText.getSelectedItem().toString() == "si"){
                        nuovo.setDietaSeguita(true);
                    }
                    else{
                        nuovo.setDietaSeguita(false);
                    }
                    nuovo.setModalitaParto(modalitaPartoText.getSelectedItem().toString());
                    if(pesoBambinoText.getText() != null){
                        Integer pesoBambino = new Integer(pesoBambinoText.getText());
                        nuovo.setPesoBambino(pesoBambino);
                    }
                    if(glicemiaNeonatoText.getText() != null){
                        nuovo.setGlicemiaNeonato(new Float(glicemiaNeonatoText.toString()));
                    }
                    if(notePersonaliText.getText() != null){
                        nuovo.setNotePersonali(notePersonaliText.getText());
                    }
                    // CAMPI ALIMENTAZIONE

                }
                else{
                    //lancio messaggio d'errore'
                    JOptionPane.showMessageDialog(new Frame(),
                            "ATTENZIONE: inserire tutti i campi obbligatori per inserire una nuova paziente",
                            "Campi obbligatori non compilati",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        inserisci.setBounds(550,510,150,25);
        mainPanel.add(inserisci);
    }

    public static int numeroMese(String meseString){
        if (meseString == "Gennaio") {
            return 1;
        } else if (meseString == "Febbraio") {
            return 2;
        } else if (meseString == "Marzo") {
            return 3;
        } else if (meseString == "Aprile") {
            return 4;
        } else if (meseString == "maggio") {
            return 5;
        } else if (meseString == "Giugno") {
            return 6;
        } else if (meseString == "Luglio") {
            return 7;
        } else if (meseString == "Agosto") {
            return 8;
        } else if (meseString == "Settembre") {
            return 9;
        } else if (meseString == "Ottobre") {
            return 10;
        } else if (meseString == "Novembre") {
            return 11;
        } else if (meseString == "Dicembre") {
            return 12;
        }
        return 0;
    }
}