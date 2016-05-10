import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 10/05/2016.
 */
public class ModificaPaziente extends JFrame {
    public static String dataToString(GregorianCalendar data){
        Integer giorno = data.get(GregorianCalendar.DATE);
        Integer mese = data.get(GregorianCalendar.MONTH)+1;
        Integer anno = data.get(GregorianCalendar.YEAR);
        return new String(giorno + "/"  + mese + "/" + anno);
    }

    public ModificaPaziente(final Paziente p) {
        super(p.getNome() + " " + p.getCognome());
        super.setPreferredSize(new Dimension(800, 550));
        setSize(new Dimension(800, 550));
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel nomeLabel = new JLabel("Nome: " + p.getNome() + "  " + p.getCognome());
        nomeLabel.setBounds(10, 10, 250, 25);
        mainPanel.add(nomeLabel);

        JLabel dataDiNascitaLabel = new JLabel("Data di Nascita: " + dataToString(p.getDataDiNascita()));
        dataDiNascitaLabel.setBounds(10, 40, 250, 25);
        mainPanel.add(dataDiNascitaLabel);

        JLabel nazionalitaLabel = new JLabel("Nazionalita': " + p.getNazionalita());
        nazionalitaLabel.setBounds(10, 70, 250, 25);
        mainPanel.add(nazionalitaLabel);

        JLabel telefonoLabel = new JLabel("Telefono: ");
        telefonoLabel.setBounds(10, 100, 250, 25);
        mainPanel.add(telefonoLabel);

        final JTextField telefonoText = new JTextField(p.getTelefono());
        telefonoText.setBounds(90,100,80,25);
        mainPanel.add(telefonoText);

        JLabel infoGravidanzaLabel = new JLabel("INFORMAZIONI GRAVIDANZA:");
        infoGravidanzaLabel.setBounds(10, 130, 250, 25);
        mainPanel.add(infoGravidanzaLabel);

        JLabel paritaLabel = new JLabel("Parita': ");
        paritaLabel.setBounds(10, 160, 250, 25);
        mainPanel.add(paritaLabel);

        final JTextField paritaText = new JTextField(p.getParita());
        paritaText.setBounds(90,160,75,25);
        mainPanel.add(paritaText);

        JLabel dataUltimaMestruazioneLabel = new JLabel("Data ultima mestruazione: " + dataToString(p.getUltimaMestruazione()));
        dataUltimaMestruazioneLabel.setBounds(10, 190, 250, 25);
        mainPanel.add(dataUltimaMestruazioneLabel);

        String[] giornoSelect = new String[31];
        for(int i=0; i < 31 ; ++i){
            Integer giornoInteger = new Integer(i+1);
            giornoSelect[i] = giornoInteger.toString();
        }
        String[] meseSelect = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        String[] annoSelectBreve = {"2015" , "2016"};


        JLabel pesoInizioGravidanzaLabel = new JLabel("Peso inizio gravidanza: ");
        pesoInizioGravidanzaLabel.setBounds(10, 220, 250, 25);
        mainPanel.add(pesoInizioGravidanzaLabel);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Float.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        Float pesoFloat = new Float(p.getPesoInizioGravidanza());
        String pesoInizioGravidanzaString = new String(pesoFloat.toString());
        final JTextField pesoInizioGravidanzaText = new JTextField(pesoInizioGravidanzaString);
        pesoInizioGravidanzaText.setBounds(170,220,70,25);
        mainPanel.add(pesoInizioGravidanzaText);
        JLabel kilogrammi = new JLabel("Kg");
        kilogrammi.setBounds(245,220,20,25);
        mainPanel.add(kilogrammi);

        JLabel pesoFineGravidanzaLabel = new JLabel("Peso fine gravidanza: ");
        pesoFineGravidanzaLabel.setBounds(10, 250, 250, 25);
        mainPanel.add(pesoFineGravidanzaLabel);

        Float pesoFineGravidanzaFloat = new Float(p.getPesoFineGravidanza());
        final JTextField pesoFineGravidanzaText = new JTextField(pesoFineGravidanzaFloat.toString());
        pesoFineGravidanzaText.setBounds(170,250,70,25);
        mainPanel.add(pesoFineGravidanzaText);
        JLabel kilogrammi_2 = new JLabel("Kg");
        kilogrammi_2.setBounds(245,250,20,25);
        mainPanel.add(kilogrammi_2);

        JLabel modalitaPartoLabel = new JLabel("Modalita' parto: ");
        modalitaPartoLabel.setBounds(10, 280, 230, 25);
        mainPanel.add(modalitaPartoLabel);

        String[] modalitaPartoSelect = {"non specificato","spontaneo", "indotto", "operativo", "taglio cesareo"};
        final JComboBox<String>  modalitaPartoText = new JComboBox<>(modalitaPartoSelect);
        if(p.getModalitaParto() == "non specificato"){
            modalitaPartoText.setSelectedIndex(0);
        }
        else if(p.getModalitaParto() == "spontaneo"){
            modalitaPartoText.setSelectedIndex(1);
        }
        else if(p.getModalitaParto() == "indotto"){
            modalitaPartoText.setSelectedIndex(2);
        }
        else if(p.getModalitaParto() == "operativo"){
            modalitaPartoText.setSelectedIndex(3);
        }
        else if(p.getModalitaParto() == "taglio cesareo"){
            modalitaPartoText.setSelectedIndex(4);
        }
        modalitaPartoText.setBounds(130, 280, 120, 25);
        mainPanel.add(modalitaPartoText);


        JLabel pesoBambinoLabel = new JLabel("Peso neonato: ");
        pesoBambinoLabel.setBounds(10, 310, 250, 25);
        mainPanel.add(pesoBambinoLabel);

        Integer pesoNeonatoInteger = new Integer(p.getPesoBambino());
        final JTextField pesoBambinoText = new JTextField(pesoNeonatoInteger.toString());
        pesoBambinoText.setBounds(130,310,70,25);
        mainPanel.add(pesoBambinoText);
        JLabel grammi = new JLabel("gr");
        grammi.setBounds(205,310,20,25);
        mainPanel.add(grammi);

        JLabel glicemiaNeonatoLabel = new JLabel("Glicemia neonato: ");
        glicemiaNeonatoLabel.setBounds(10, 340, 250, 25);
        mainPanel.add(glicemiaNeonatoLabel);

        Float glicemiaNeonatoFloat = new Float(p.getGlicemiaNeonato());
        final JTextField glicemiaNeonatoText = new JTextField(glicemiaNeonatoFloat.toString());
        glicemiaNeonatoText.setBounds(130,340,70,25);
        mainPanel.add(glicemiaNeonatoText);

        JLabel infoDiabeteLabel = new JLabel("INFORMAZIONI DIABETE:");
        infoDiabeteLabel.setBounds(10, 370, 250, 25);
        mainPanel.add(infoDiabeteLabel);

        JLabel terapiaLabel = new JLabel("Terapia: " );
        terapiaLabel.setBounds(10, 400, 250, 25);
        mainPanel.add(terapiaLabel);

        String[] terapiaSelect = {"non specificato","insulinica", "dieta"};
        final JComboBox<String> terapiatext = new JComboBox<>(terapiaSelect);
        if(p.getTerapia() == "non specificato"){
            terapiatext.setSelectedIndex(0);
        }
        else if(p.getTerapia() == "insulinica"){
            terapiatext.setSelectedIndex(1);
        }
        else if(p.getTerapia() == "dieta"){
            terapiatext.setSelectedIndex(2);
        }
        terapiatext.setBounds(130,400,130,25);
        mainPanel.add(terapiatext);

        JLabel emoglobinaGlicataLabel = new JLabel("Emoglobina glicata: ");
        emoglobinaGlicataLabel.setBounds(10, 430, 250, 25);
        mainPanel.add(emoglobinaGlicataLabel);

        Integer emoglobinaGlicataInteger = new Integer(p.getEmoglobinaGlicata());
        final JTextField emoglobinaGlicataText = new JTextField(emoglobinaGlicataInteger.toString());
        emoglobinaGlicataText.setBounds(130,430,70,25);
        mainPanel.add(emoglobinaGlicataText);

        JLabel dietaSeguitaLabel = new JLabel("Dieta seguita: " );
        dietaSeguitaLabel.setBounds(10, 460, 250, 25);
        mainPanel.add(dietaSeguitaLabel);

        String[] dietaSeguitaSelect = {"si", "no"};
        final JComboBox<String> dietaSeguitaText = new JComboBox<>(dietaSeguitaSelect);
        if(p.getDietaSeguita() == true){
            dietaSeguitaText.setSelectedIndex(0);
        }
        else{
            dietaSeguitaText.setSelectedIndex(1);
        }
        dietaSeguitaText.setBounds(130,460,85,25);
        mainPanel.add(dietaSeguitaText);

        JLabel tipologiaDiabeteLabel = new JLabel("Tipologia diabete: ");
        tipologiaDiabeteLabel.setBounds(10, 490, 250, 25);
        mainPanel.add(tipologiaDiabeteLabel);

        String[] tipologiaDiabeteSelect = {"gestazionale","pregestazionale"};
        final JComboBox<String> tipologiaDiabeteText = new JComboBox<>(tipologiaDiabeteSelect);
        if(p.getTipologiaDiabete() == "gestazionale"){
            tipologiaDiabeteText.setSelectedIndex(0);
        }
        else{
            tipologiaDiabeteText.setSelectedIndex(1);
        }
        tipologiaDiabeteText.setBounds(130,490,130,25);
        mainPanel.add(tipologiaDiabeteText);

        //COLONNA CENTRALE BOUNDS()
        JLabel alimentazioneLabel = new JLabel("ALIMENTAZIONE SETTIMANALE:");
        alimentazioneLabel.setBounds(300, 10, 250, 25);
        mainPanel.add(alimentazioneLabel);

        JLabel carboidratiLabel = new JLabel("Carboidrati: ");
        carboidratiLabel.setBounds(300, 40, 100, 25);
        mainPanel.add(carboidratiLabel);

        String[] alimentazioneSelect = {"meno di 2", "dalle 3 alle 5", "piu' di 6"};

        final JComboBox<String> carboidratiText = new JComboBox<>(alimentazioneSelect);
        if(p.getAlimentazionePreconcezionale().carbroidati == "meno di 2"){
            carboidratiText.setSelectedIndex(0);
        }
        else if(p.getAlimentazionePreconcezionale().carbroidati == "dalle 3 alle 5"){
            carboidratiText.setSelectedIndex(1);
        }
        else if(p.getAlimentazionePreconcezionale().carbroidati == "piu' di 6"){
            carboidratiText.setSelectedIndex(2);
        }
        carboidratiText.setBounds(400,40,100,25);
        mainPanel.add(carboidratiText);

        JLabel carboidratiComplessiLabel = new JLabel("CarboidratiC: ");
        carboidratiComplessiLabel.setBounds(300, 70, 100, 25);
        mainPanel.add(carboidratiComplessiLabel);

        final JComboBox<String> carboidratiComplessiText = new JComboBox<>(alimentazioneSelect);
        if(p.getAlimentazionePreconcezionale().carbroidatiComplessi == "meno di 2"){
            carboidratiComplessiText.setSelectedIndex(0);
        }
        else if(p.getAlimentazionePreconcezionale().carbroidatiComplessi == "dalle 3 alle 5"){
            carboidratiComplessiText.setSelectedIndex(1);
        }
        else if(p.getAlimentazionePreconcezionale().carbroidatiComplessi == "piu' di 6"){
            carboidratiComplessiText.setSelectedIndex(2);
        }
        carboidratiComplessiText.setBounds(400, 70, 100, 25);
        mainPanel.add(carboidratiComplessiText);

        JLabel proteineLabel = new JLabel("Proteine: ");
        proteineLabel.setBounds(300, 100, 100, 25);
        mainPanel.add(proteineLabel);

        final JComboBox<String> proteineText = new JComboBox<>(alimentazioneSelect);
        if(p.getAlimentazionePreconcezionale().proteine == "meno di 2"){
            proteineText.setSelectedIndex(0);
        }
        else if(p.getAlimentazionePreconcezionale().proteine == "dalle 3 alle 5"){
            proteineText.setSelectedIndex(1);
        }
        else if(p.getAlimentazionePreconcezionale().proteine == "piu' di 6"){
            proteineText.setSelectedIndex(2);
        }
        proteineText.setBounds(400,100,100,25);
        mainPanel.add(proteineText);

        JLabel grassiLabel = new JLabel("Grassi:");
        grassiLabel.setBounds(300,130,150,25);
        mainPanel.add(grassiLabel);

        final JComboBox<String> grassiText = new JComboBox<>(alimentazioneSelect);
        if(p.getAlimentazionePreconcezionale().grassi == "meno di 2"){
            grassiText.setSelectedIndex(0);
        }
        else if(p.getAlimentazionePreconcezionale().grassi == "dalle 3 alle 5"){
            grassiText.setSelectedIndex(1);
        }
        else if(p.getAlimentazionePreconcezionale().grassi == "piu' di 6"){
            grassiText.setSelectedIndex(2);
        }
        grassiText.setBounds(400,130,100,25);
        mainPanel.add(grassiText);


        JLabel ecografiaTerzoTrimestreLabel = new JLabel("ECOGRAFIA TERZO TRIMESTRE:");
        ecografiaTerzoTrimestreLabel.setBounds(300, 160, 250, 25);
        mainPanel.add(ecografiaTerzoTrimestreLabel);


            JLabel dbpTerzoTrimetreLabel = new JLabel("DBP: ");
            dbpTerzoTrimetreLabel.setBounds(300, 190, 80, 25);
            mainPanel.add(dbpTerzoTrimetreLabel);

            JLabel ccTerzoTrimetreLabel = new JLabel("CC: ");
            ccTerzoTrimetreLabel.setBounds(400, 190, 80, 25);
            mainPanel.add(ccTerzoTrimetreLabel);

            JLabel caTerzoTrimestreLabel = new JLabel("CA: ");
            caTerzoTrimestreLabel.setBounds(300, 220, 80, 25);
            mainPanel.add(caTerzoTrimestreLabel);

            JLabel efwTerzoTrimestre = new JLabel("EFW: ");
            efwTerzoTrimestre.setBounds(400, 220, 80, 25);
            mainPanel.add(efwTerzoTrimestre);

            JLabel laTerzoTrimestreLabel = new JLabel("LA: ");
            laTerzoTrimestreLabel.setBounds(300, 250, 150, 25);
            mainPanel.add(laTerzoTrimestreLabel);



            final JTextField dbpText = new JTextField();
            dbpText.setBounds(335,190,50,25);
            mainPanel.add(dbpText);

            final JTextField ccText = new JTextField();
            ccText.setBounds(435,190,50,25);
            mainPanel.add(ccText);

            final JTextField caText = new JTextField();
            caText.setBounds(335,220,50,25);
            mainPanel.add(caText);

            final JTextField efwText = new JTextField();
            efwText.setBounds(435,220,50,25);
            mainPanel.add(efwText);

            String[] laSelect = {"non definito", "regolare", "scarso", "abbondante"};
            final JComboBox<String> laText = new JComboBox<>(laSelect);
            laText.setBounds(335,250,100,25);
            mainPanel.add(laText);

            final JComboBox<String> giornoText3 = new JComboBox<>(giornoSelect);
            final JComboBox<String> meseText3 = new JComboBox<>(meseSelect);
            final JComboBox<String> annoText3 = new JComboBox<>(annoSelectBreve);

        if(p.getEcografiaTerzoTrimestre() != null){

            JLabel dataTerzoTrimestre = new JLabel("Data: " + dataToString(p.getEcografiaTerzoTrimestre().dataEsecuzione));
            dataTerzoTrimestre.setBounds(300, 280, 150, 25);
            mainPanel.add(dataTerzoTrimestre);

            dbpText.setText(new Float(p.getEcografiaTerzoTrimestre().DBP).toString());
            ccText.setText(new Float(p.getEcografiaTerzoTrimestre().CC).toString());
            caText.setText(new Float(p.getEcografiaTerzoTrimestre().CA).toString());
            efwText.setText(new Integer(p.getEcografiaTerzoTrimestre().EFW).toString());
            if(p.getEcografiaTerzoTrimestre().LA == "non definito"){
                laText.setSelectedIndex(0);
            }
            else if(p.getEcografiaTerzoTrimestre().LA == "regolare"){
                laText.setSelectedIndex(1);
            }
            else if(p.getEcografiaTerzoTrimestre().LA == "scarso"){
                laText.setSelectedIndex(2);
            }
            else{
                laText.setSelectedIndex(3);
            }
        } else{

            giornoText3.setBounds(300,280,50,25);
            mainPanel.add(giornoText3);

            meseText3.setBounds(350,280,80,25);
            mainPanel.add(meseText3);

            annoText3.setBounds(430,280,60,25);
            mainPanel.add(annoText3);
        }

            JLabel ecografiaOstetricaLabel = new JLabel("ECOGRAFIA OSTETRICA:");
            ecografiaOstetricaLabel.setBounds(300, 310, 250, 25);
            mainPanel.add(ecografiaOstetricaLabel);

            JLabel dbpOstetrica = new JLabel("DBP: ");
            dbpOstetrica.setBounds(300, 340, 80, 25);
            mainPanel.add(dbpOstetrica);

            final JTextField dbpText2 = new JTextField();
            dbpText2.setBounds(335,340,50,25);
            mainPanel.add(dbpText2);

            JLabel ccOstetrica = new JLabel("CC: ");
            ccOstetrica.setBounds(400, 340, 80, 25);
            mainPanel.add(ccOstetrica);

            final JTextField ccText2 = new JTextField();
            ccText2.setBounds(435,340,50,25);
            mainPanel.add(ccText2);

            JLabel caOstetrica = new JLabel("CA: ");
            caOstetrica.setBounds(300, 370, 80, 25);
            mainPanel.add(caOstetrica);

            final JTextField caText2 = new JTextField();
            caText2.setBounds(335,370,50,25);
            mainPanel.add(caText2);

            JLabel efwOstetrica = new JLabel("EFW: ");
            efwOstetrica.setBounds(400, 370, 80, 25);
            mainPanel.add(efwOstetrica);

            final JTextField efwText2 = new JTextField();
            efwText2.setBounds(435,370,50,25);
            mainPanel.add(efwText2);

            JLabel laOstetrica = new JLabel("LA: ");
            laOstetrica.setBounds(300, 400, 150, 25);
            mainPanel.add(laOstetrica);

            final JComboBox<String> laText2 = new JComboBox<>(laSelect);
            laText2.setBounds(335, 400,100,25);
            mainPanel.add(laText2);

            final JComboBox<String> annoText4 = new JComboBox<>(annoSelectBreve);
            final JComboBox<String> meseText4 = new JComboBox<>(meseSelect);
            final JComboBox<String> giornoText4 = new JComboBox<>(giornoSelect);

            if(p.getEcografiaOstetrica() != null){

                JLabel dataOstetrica = new JLabel("Data:" + dataToString(p.getEcografiaOstetrica().dataEsecuzione));
                dataOstetrica.setBounds(300, 430, 150, 25);
                mainPanel.add(dataOstetrica);

                dbpText2.setText(new Float(p.getEcografiaOstetrica().DBP).toString());
                ccText2.setText(new Float(p.getEcografiaOstetrica().CC).toString());
                caText2.setText(new Float(p.getEcografiaOstetrica().CA).toString());
                efwText2.setText(new Integer(p.getEcografiaOstetrica().EFW).toString());
                if(p.getEcografiaOstetrica().LA == "non definito"){
                    laText2.setSelectedIndex(0);
                }
                else if(p.getEcografiaOstetrica().LA == "regolare"){
                    laText2.setSelectedIndex(1);
                }
                else if(p.getEcografiaOstetrica().LA == "scarso"){
                    laText2.setSelectedIndex(2);
                }
                else{
                    laText2.setSelectedIndex(3);
                }
            } else{

                giornoText4.setBounds(300,430,50,25);
                mainPanel.add(giornoText4);

                meseText4.setBounds(350,430,80,25);
                mainPanel.add(meseText4);

                annoText4.setBounds(430,430,60,25);
                mainPanel.add(annoText4);
            }


        JLabel notePersonaliLabel = new JLabel("NOTE PERSONALI:");
        notePersonaliLabel.setBounds(530, 10, 250, 25);
        mainPanel.add(notePersonaliLabel);

        final JTextArea notePersonaliArea = new JTextArea(p.getNotePersonali());
        notePersonaliArea.setEditable(true);
        notePersonaliArea.setLineWrap(true);
        notePersonaliArea.setWrapStyleWord(true);
        notePersonaliArea.setBounds(530, 40, 230, 150);
        mainPanel.add(notePersonaliArea);

        final JButton salva = new JButton("SALVA");
        salva.setBounds(640, 450, 120, 30);
        mainPanel.add(salva);

        salva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aggiorno tutti i campi della paziente:
                // telefono
                if(!(telefonoText.getText().equals(""))){
                    p.setTelefono(telefonoText.getText());
                }

                // parità
                if(!(paritaText.getText().equals(""))){
                    p.setParita(paritaText.getText());
                }

                // terapia
                p.setTerapia(terapiatext.getSelectedItem().toString());

                //emoglobina glicata
                if(!(emoglobinaGlicataText.getText().equals(""))){
                    Integer emoglobinaGlicata = new Integer(emoglobinaGlicataText.getText());
                    p.setEmoglobinaGlicata(emoglobinaGlicata);
                }

                // dieta seguita
                if(dietaSeguitaText.getSelectedItem().toString() == "si"){
                    p.setDietaSeguita(true);
                } else{
                    p.setDietaSeguita(false);
                }

                // modalità parto
                p.setModalitaParto(modalitaPartoText.getSelectedItem().toString());

                // peso bambino
                if(!(pesoBambinoText.getText().equals(""))){
                    Integer pesoBambino = new Integer(pesoBambinoText.getText().toString());
                    p.setPesoBambino(pesoBambino);
                }

                //glicemia neonato
                if(!(glicemiaNeonatoText.getText().equals(""))){
                    p.setGlicemiaNeonato(new Float(glicemiaNeonatoText.getText()));
                }

                // note personali
                if(!(notePersonaliArea.getText().equals(""))){
                    p.setNotePersonali(notePersonaliArea.getText());
                }

                //CAMPI ALIMENTAZIONE
                String carboidrati = carboidratiText.getSelectedItem().toString();
                String carboidratiComplessi = carboidratiComplessiText.getSelectedItem().toString();
                String proteine = proteineText.getSelectedItem().toString();
                String grassi = grassiText.getSelectedItem().toString();
                p.setAlimentazionePreconcezionale(carboidrati, carboidratiComplessi, proteine, grassi);

                //ECOGRAFIE
                //ecografia terzo trimestre
                System.out.println(dbpText.getText().isEmpty());
                if (!(dbpText.getText().isEmpty() && ccText.getText().isEmpty() && caText.getText().isEmpty() && efwText.getText().isEmpty())) {
                    Float dbp = new Float(dbpText.getText());
                    Float cc = new Float(ccText.getText());
                    Float ca = new Float(caText.getText());
                    Integer efw = new Integer(efwText.getText());
                    if(p.getEcografiaTerzoTrimestre() == null) {
                        Integer giornoEcografiaTerzoTrimetre = new Integer(giornoText3.getSelectedItem().toString());
                        Integer meseEcografiaTerzoTrimetre = new Integer(numeroMese(meseText3.getSelectedItem().toString()));
                        Integer annoEcografiaTerzoTrimestre = new Integer(annoText3.getSelectedItem().toString());
                        GregorianCalendar dataEcografiaTerzoTrimetre = new GregorianCalendar(annoEcografiaTerzoTrimestre, meseEcografiaTerzoTrimetre, giornoEcografiaTerzoTrimetre);
                        p.setEcografiaTerzoTrimestre(dbp, cc, ca, efw, laText.getSelectedItem().toString(), dataEcografiaTerzoTrimetre);
                    }
                    else{
                        p.setEcografiaTerzoTrimestre(dbp, cc, ca, efw, laText.getSelectedItem().toString(), p.getEcografiaTerzoTrimestre().dataEsecuzione);
                    }
                } else if(!(dbpText.getText().isEmpty() || ccText.getText().isEmpty() || caText.getText().isEmpty() || efwText.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(new Frame(),
                            "ATTENZIONE: per inserire una ecografia terzo trimestre inserire tutti i dati richiesti",
                            "Campi obbligatori non compilati",
                            JOptionPane.WARNING_MESSAGE);
                }
                //ecografia ostetrica
                System.out.println(dbpText2.getText().isEmpty());
                if (!(dbpText2.getText().isEmpty() && ccText2.getText().isEmpty() && caText2.getText().isEmpty() && efwText2.getText().isEmpty())) {
                    Float dbp2 = new Float(dbpText2.getText());
                    Float cc2 = new Float(ccText2.getText());
                    Float ca2 = new Float(caText2.getText());
                    Integer efw2 = new Integer(efwText2.getText());
                    if(p.getEcografiaOstetrica() == null) {
                        Integer giornoEcografiaOstetrica = new Integer(giornoText4.getSelectedItem().toString());
                        Integer meseEcografiaOstetrica = new Integer(numeroMese(meseText4.getSelectedItem().toString()));
                        Integer annoEcografiaOstetrica = new Integer(annoText4.getSelectedItem().toString());
                        GregorianCalendar dataEcografiaOstetrica = new GregorianCalendar(annoEcografiaOstetrica, meseEcografiaOstetrica, giornoEcografiaOstetrica);
                        p.setEcografiaOstetrica(dbp2, cc2, ca2, efw2, laText2.getSelectedItem().toString(), dataEcografiaOstetrica);
                    }
                    else{
                        p.setEcografiaOstetrica(dbp2, cc2, ca2, efw2, laText2.getSelectedItem().toString(), p.getEcografiaOstetrica().dataEsecuzione);
                    }
                }
                else if(!(dbpText2.getText().isEmpty() || ccText2.getText().isEmpty() || caText2.getText().isEmpty() || efwText2.getText().isEmpty())){
                    JOptionPane.showMessageDialog(new Frame(),
                            "ATTENZIONE: per inserire una ecografia ostetrica inserire tutti i dati richiesti",
                            "Campi obbligatori non compilati",
                            JOptionPane.WARNING_MESSAGE);
                }
                JOptionPane.showMessageDialog(new Frame(),
                        "Paziente aggiornato correttamente",
                        "Aziene eseguita correttamente",
                        JOptionPane.INFORMATION_MESSAGE);
                Container frame = salva.getParent();
                do
                    frame = frame.getParent();
                while (!(frame instanceof JFrame));
                ((JFrame) frame).dispose();

            }
        });
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
