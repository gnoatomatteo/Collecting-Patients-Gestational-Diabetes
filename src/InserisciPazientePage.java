import javax.swing.*;

/**
 * Created by break_000 on 17/03/2016.
 */
public class InserisciPazientePage extends JFrame {
    private String nome;
    private String cognome;
    private String nazionalita;
    private String telefono; // fare parser di controllo
    private String parita;
    private float pesoiniziogravidanza;
    private float pesoFineGravidanza;
    private String terapia;
    private int embologiaGlicata;
    private boolean dietaSeguita;
    private String tipologiaDiabete; // scelte fissate
    private String modalitaParto;
    private int pesoBambino;
    private String notePersonali;
    private float glicemiaNeonato;

    //elementi ecografiaTerzoTrimestre
    private float bpb_terzoTrimestre;
    private float cc_terzoTrimestre;
    private float ca_terzoTrimestre;
    private float efw_terzoTrimestre;
    private String la_terzoTrimestre;

    //elementi ecografiaOstetrica
    private float Bpb_ostetrica;
    private float cc_ostetrica;
    private float ca_ostetrica;
    private float efw_ostetrica;
    private String la_ostetrica;

    //elementi visivi
    private JLabel nomeLabel;
    private JLabel cognomeLabel;
    private JLabel dataDiNascitaLabel;
    private JLabel telefonoLabel;
    private JLabel paritaLabel;
    private JLabel pesoInizioGravidanzaLabel;
    private JLabel pesiFinegravidanzaLabel;
    private JLabel terapiaLabel;
    private JLabel embologiaGlicataLabel;
    private JLabel dietaSeguitaLabel;
    private JLabel tipologiadiabete;
    private JLabel modalitaPartoLabel;
    private JLabel pesoBambinoLabel;
    private JLabel glicemiaNeonatoLabel;
    private JLabel notePersonaliLabel;
    private JLabel ecografiaTerzoTrimestreLabel;
    private JLabel ecografiaOstetricaLabel;
    private JLabel btpLabel;
    private JLabel ccLabel;
    private JLabel caLabel;
    private JLabel efwLabel;
    private JLabel laLabel;
    private JLabel dataEsecuzioneLabel;
    private JTextField nomeText;
    private JTextField cognomeText;
    private JTextField telefonoText;
    private JTextField nazionalitaText;
    private JTextField paritaText;
    private JTextField terapiaText;
    private JTextField tipologiaDiabeteText;
    private JTextField modalitaPartoText;
    private JTextArea notePersonaliTextArea;

}
