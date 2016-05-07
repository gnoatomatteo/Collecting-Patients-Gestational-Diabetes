import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

/**
 * Created by break_000 on 28/04/2016.
 */
public class InfoPazientePage extends JFrame {
    public static String dataToString(GregorianCalendar data){
        Integer giorno = data.get(GregorianCalendar.DATE);
        Integer mese = data.get(GregorianCalendar.MONTH)+1;
        Integer anno = data.get(GregorianCalendar.YEAR);
        return new String(giorno + "/"  + mese + "/" + anno);
    }

    public InfoPazientePage(Paziente p){
        super(p.getNome() + " "  + p.getCognome());
        super.setPreferredSize(new Dimension(800,550));
        setSize(new Dimension(800,550));
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel nomeLabel = new JLabel("Nome: " + p.getNome() + "  " + p.getCognome());
        nomeLabel.setBounds(10,10,250,25);
        mainPanel.add(nomeLabel);

        JLabel dataDiNascitaLabel = new JLabel("Data di Nascita: " + dataToString(p.getDataDiNascita()));
        dataDiNascitaLabel.setBounds(10,40,250,25);
        mainPanel.add(dataDiNascitaLabel);

        JLabel nazionalitaLabel = new JLabel("Nazionalita': " + p.getNazionalita());
        nazionalitaLabel.setBounds(10,70,250,25);
        mainPanel.add(nazionalitaLabel);

        JLabel telefonoLabel = new JLabel("Telefono: " + p.getTelefono());
        telefonoLabel.setBounds(10,100,250,25);
        mainPanel.add(telefonoLabel);

        JLabel infoGravidanzaLabel = new JLabel("INFORMAZIONI GRAVIDANZA:");
        infoGravidanzaLabel.setBounds(10,130,250,25);
        mainPanel.add(infoGravidanzaLabel);

        JLabel paritaLabel = new JLabel("Parita': " + p.getParita());
        paritaLabel.setBounds(10,160,250,25);
        mainPanel.add(paritaLabel);

        JLabel dataUltimaMestruazioneLabel = new JLabel("Data ultima mestruazione: " + dataToString(p.getUltimaMestruazione()));
        dataUltimaMestruazioneLabel.setBounds(10,190,250,25);
        mainPanel.add(dataUltimaMestruazioneLabel);

        JLabel pesoInizioGravidanzaLabel = new JLabel("Peso inizio gravidanza: " + p.getPesoInizioGravidanza());
        pesoInizioGravidanzaLabel.setBounds(10,220,250,25);
        mainPanel.add(pesoInizioGravidanzaLabel);

        JLabel pesoFineGravidanzaLabel = new JLabel("Peso fine gravidanza: " + p.getPesoFineGravidanza());
        pesoFineGravidanzaLabel.setBounds(10,250,250,25);
        mainPanel.add(pesoFineGravidanzaLabel);

        JLabel modalitaPartoLabel = new JLabel("Modalita' parto: " + p.getModalitaParto());
        modalitaPartoLabel.setBounds(10,280,250,25);
        mainPanel.add(modalitaPartoLabel);

        JLabel pesoBambinoLabel = new JLabel("Peso neonato: " + p.getPesoBambino());
        pesoBambinoLabel.setBounds(10,310,250,25);
        mainPanel.add(pesoBambinoLabel);

        JLabel glicemiaNeonatoLabel = new JLabel("Glicemia neonato: " + p.getGlicemiaNeonato());
        glicemiaNeonatoLabel.setBounds(10,340,250,25);
        mainPanel.add(glicemiaNeonatoLabel);

        JLabel infoDiabeteLabel = new JLabel("INFORMAZIONI DIABETE:");
        infoDiabeteLabel.setBounds(10,370,250,25);
        mainPanel.add(infoDiabeteLabel);

        JLabel terapiaLabel = new JLabel("Terapia: " + p.getTerapia());
        terapiaLabel.setBounds(10,400,250,25);
        mainPanel.add(terapiaLabel);

        JLabel emoglobinaGlicataLabel = new JLabel("Emoglobina glicata: " + p.getEmoglobinaGlicata());
        emoglobinaGlicataLabel.setBounds(10,430,250,25);
        mainPanel.add(emoglobinaGlicataLabel);

        JLabel dietaSeguitaLabel = new JLabel("Dieta seguita: " + p.getDietaSeguita());
        dietaSeguitaLabel.setBounds(10, 460, 250, 25);
        mainPanel.add(dietaSeguitaLabel);

        JLabel tipologiaDiabeteLabel = new JLabel("Tipologia diabete: " + p.getTipologiaDiabete());
        tipologiaDiabeteLabel.setBounds(10,490,250,25);
        mainPanel.add(tipologiaDiabeteLabel);

        //COLONNA CENTRALE BOUNDS()
        JLabel alimentazioneLabel = new JLabel("ALIMENTAZIONE SETTIMANALE:");
        alimentazioneLabel.setBounds(300,10,250,25);
        mainPanel.add(alimentazioneLabel);

        JLabel carboidratiLabel = new JLabel("Carboidrati: " + p.getAlimentazionePreconcezionale().carbroidati);
        carboidratiLabel.setBounds(300, 40, 250, 25);
        mainPanel.add(carboidratiLabel);

        JLabel carboidratiComplessiLabel = new JLabel("CarboidratiC: " + p.getAlimentazionePreconcezionale().carbroidatiComplessi);
        carboidratiComplessiLabel.setBounds(300,70,250,25);
        mainPanel.add(carboidratiComplessiLabel);

        JLabel proteineLabel = new JLabel("Proteine: " + p.getAlimentazionePreconcezionale().proteine);
        proteineLabel.setBounds(300,100,250,25);
        mainPanel.add(proteineLabel);

        JLabel grassiLabel = new JLabel("Grassi: " + p.getAlimentazionePreconcezionale().grassi);
        grassiLabel.setBounds(300,130,250,25);
        mainPanel.add(grassiLabel);

        JLabel ecografiaTerzoTrimestreLabel = new JLabel("ECOGRAFIA TERZO TRIMESTRE:");
        ecografiaTerzoTrimestreLabel.setBounds(300,160,250,25);
        mainPanel.add(ecografiaTerzoTrimestreLabel);

        if(p.getEcografiaTerzoTrimestre() != null) {

            JLabel dbpTerzoTrimetreLabel = new JLabel("DBP: " + p.getEcografiaTerzoTrimestre().DBP);
            dbpTerzoTrimetreLabel.setBounds(300, 190, 80, 25);
            mainPanel.add(dbpTerzoTrimetreLabel);

            JLabel ccTerzoTrimetreLabel = new JLabel("CC: " + p.getEcografiaTerzoTrimestre().CC);
            ccTerzoTrimetreLabel.setBounds(400, 190, 80, 25);
            mainPanel.add(ccTerzoTrimetreLabel);

            JLabel caTerzoTrimestreLabel = new JLabel("CA: " + p.getEcografiaTerzoTrimestre().CA);
            caTerzoTrimestreLabel.setBounds(300, 220, 80, 25);
            mainPanel.add(caTerzoTrimestreLabel);

            JLabel efwTerzoTrimestre = new JLabel("EFW: " + p.getEcografiaTerzoTrimestre().EFW);
            efwTerzoTrimestre.setBounds(400, 220, 80, 25);
            mainPanel.add(efwTerzoTrimestre);

            JLabel laTerzoTrimestreLabel = new JLabel("LA: " + p.getEcografiaTerzoTrimestre().LA);
            laTerzoTrimestreLabel.setBounds(300, 250, 150, 25);
            mainPanel.add(laTerzoTrimestreLabel);

            JLabel dataTerzoTrimestre = new JLabel("Data: " + dataToString(p.getEcografiaTerzoTrimestre().dataEsecuzione));
            dataTerzoTrimestre.setBounds(300, 280, 150, 25);
            mainPanel.add(dataTerzoTrimestre);
        }

        if(p.getEcografiaOstetrica() != null) {
            JLabel ecografiaOstetricaLabel = new JLabel("ECOGRAFIA OSTETRICA:");
            ecografiaOstetricaLabel.setBounds(300, 310, 250, 25);
            mainPanel.add(ecografiaOstetricaLabel);

            JLabel dbpOstetrica = new JLabel("DBP: " + p.getEcografiaOstetrica().DBP);
            dbpOstetrica.setBounds(300, 340, 80, 25);
            mainPanel.add(dbpOstetrica);

            JLabel ccOstetrica = new JLabel("CC: " + p.getEcografiaOstetrica().CC);
            ccOstetrica.setBounds(400, 340, 80, 25);
            mainPanel.add(ccOstetrica);

            JLabel caOstetrica = new JLabel("CA: " + p.getEcografiaOstetrica().CA);
            caOstetrica.setBounds(300, 370, 80, 25);
            mainPanel.add(caOstetrica);

            JLabel efwOstetrica = new JLabel("EFW: " + p.getEcografiaOstetrica().EFW);
            efwOstetrica.setBounds(400, 370, 80, 25);
            mainPanel.add(efwOstetrica);

            JLabel laOstetrica = new JLabel("LA: " + p.getEcografiaOstetrica().LA);
            laOstetrica.setBounds(300, 400, 150, 25);
            mainPanel.add(laOstetrica);

            JLabel dataOstetrica = new JLabel("Data:" + dataToString(p.getEcografiaOstetrica().dataEsecuzione));
            dataOstetrica.setBounds(300, 430, 150, 25);
            mainPanel.add(dataOstetrica);
        }

        JLabel notePersonaliLabel = new JLabel("NOTE PERSONALI:");
        notePersonaliLabel.setBounds(530,10,250,25);
        mainPanel.add(notePersonaliLabel);

        JTextArea notePersonaliArea = new JTextArea(p.getNotePersonali());
        notePersonaliArea.setEditable(false);
        notePersonaliArea.setLineWrap(true);
        notePersonaliArea.setWrapStyleWord(true);
        notePersonaliArea.setBounds(530,40,230,150);
        mainPanel.add(notePersonaliArea);

        JButton modifica = new JButton("MODIFICA");
        modifica.setBounds(640,450, 120, 30);
        mainPanel.add(modifica);
    }
}
