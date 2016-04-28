import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 10/03/2016.
 */

class Alimentazione{
    public String carbroidati;
    public String carbroidatiComplessi;
    public String proteine;
    public String grassi;
}

class Ecografia{
    public float DBP;
    public float CC;
    public float CA;
    public int EFW;
    public String LA; // enum("scarso", "regolare", "abbondante");
    public GregorianCalendar dataEsecuzione;

    public Ecografia(float btp, float cc, float ca, int efw, String la, GregorianCalendar gc){
        DBP = btp;
        CC = cc;
        CA = ca;
        EFW = efw;
        LA = new String(la); // regolare, scarso, abbondante
        dataEsecuzione = new GregorianCalendar(gc.get(GregorianCalendar.DATE), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.YEAR));
    }
}

public class Paziente {

    /*CAMPI DATI STATISTICI*/
    private String nome; // obbligatorio
    private String cognome; // obbligatorio
    private GregorianCalendar dataDiNascita; //obbligatorio
    private String telefono;
    private String nazionalita; // obbligatorio
    private String parita;
    private GregorianCalendar ultimaMestruazione;
    private float pesoInizioGravidanza; // obbligatorio
    private float pesoFineGravidanza;
    private Alimentazione alimentazionePreconcezionale;
    private String terapia;
    private int emoglobinaGlicata;
    private boolean dietaSeguita;
    private String tipologiaDiabete; // obbligatorio
    private String modalitaParto;
    private int pesoBambino;
    private String notePersonali;
    private Ecografia ecografiaTerzoTrimestre;
    private Ecografia ecografiaOstetrica;
    private float glicemiaNeonato;

    /*CAMPI DATI NON STATISTICI*/
    private GregorianCalendar dataInserimento;
    private int numeroPaziente;
    private static SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

    /*COSTRUTTORE*/
    public Paziente(String n, String c, GregorianCalendar dn, String naz, float pig,String td){
        nome=n;
        cognome=c;
        dataDiNascita=dn;
        nazionalita=naz;
        pesoInizioGravidanza=pig;
        tipologiaDiabete=td;
        dataInserimento = new GregorianCalendar();
    }
    /*METODI SET*/
    public void setTelefono(String t){
        telefono=t;
    }
    public void setParita(String p){
        parita=p;
    }
    public void setUltimaMestruazione(GregorianCalendar d){
        ultimaMestruazione=d;
    }
    public void setPesoFineGravidanza(float p){
        pesoFineGravidanza=p;
    }
    public void setAlimentazionePreconcezionale(String c,String cc, String p, String g){
        alimentazionePreconcezionale = new Alimentazione();
        alimentazionePreconcezionale.carbroidati=c;
        alimentazionePreconcezionale.carbroidatiComplessi=cc;
        alimentazionePreconcezionale.proteine=p;
        alimentazionePreconcezionale.grassi=g;
    }
    public void setTerapia(String t){
        terapia=t;
    }
    public void setDietaSeguita(boolean b){
        dietaSeguita=b;
    }
    public void setModalitaParto(String m){
        modalitaParto=m;
    }
    public void setPesoBambino(int g){
        pesoBambino=g;
    }
    public void setNotePersonali(String n){
        notePersonali=n;
    }
    public void setDataInserimento(GregorianCalendar d){dataInserimento=d;}
    public void setNumeroPaziente(int n) {numeroPaziente=n;}
    public void setEmoglobinaGlicata(int n){ emoglobinaGlicata = n;}
    public void setGlicemiaNeonato(float n){glicemiaNeonato=n; }
    public void setEcografiaTerzoTrimestre(float btp, float cc, float ca, int efw, String la, GregorianCalendar gc){
        ecografiaTerzoTrimestre = new Ecografia(btp,cc,ca,efw,la,gc);
    }
    public void setEcografiaOstetrica(float btp, float cc, float ca, int efw, String la, GregorianCalendar gc){
        ecografiaOstetrica= new Ecografia(btp, cc, ca, efw, la,gc);
    }



    /*METODI GET*/
    public String getNome(){
        return nome;
    }
    public String getCognome(){
        return cognome;
    }
    public GregorianCalendar getDataDiNascita(){
        return dataDiNascita;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getNazionalita(){
        return nazionalita;
    }
    public String getParita(){
        return parita;
    }
    public GregorianCalendar getUltimaMestruazione(){
        return ultimaMestruazione;
    }
    public float getPesoInizioGravidanza(){
        return pesoInizioGravidanza;
    }
    public float getPesoFineGravidanza(){
        return pesoFineGravidanza;
    }
    public Alimentazione getAlimentazionePreconcezionale(){
        return alimentazionePreconcezionale;
    }
    public String getTerapia(){
        return terapia;
    }
    public boolean getDietaSeguita(){
        return dietaSeguita;
    }
    public String getTipologiaDiabete(){
        return tipologiaDiabete;
    }
    public String getModalitaParto(){
        return modalitaParto;
    }
    public int getPesoBambino(){
        return pesoBambino;
    }
    public String getNotePersonali(){
        return notePersonali;
    }
    public GregorianCalendar getDataInserimento(){
        return dataInserimento;
    }
    public int getNumeroPaziente(){
        return numeroPaziente;
    }
    public int getEmoglobinaGlicata(){return emoglobinaGlicata;}
    public float getGlicemiaNeonato(){return glicemiaNeonato;}
    public Ecografia getEcografiaTerzoTrimestre() {return ecografiaTerzoTrimestre;}
    public Ecografia getEcografiaOstetrica() {return ecografiaOstetrica;}
    /*METODI FUNZIONALI*/
    public float aumentoPonderale(){
       return pesoFineGravidanza-pesoInizioGravidanza;
    }

    public String toString(){
        return nome + "       " + cognome + "          " + dataToString(dataDiNascita);
    }

    public static String dataToString(GregorianCalendar data){
        Integer giorno = data.get(GregorianCalendar.DATE);
        Integer mese = data.get(GregorianCalendar.MONTH)+1;
        Integer anno = data.get(GregorianCalendar.YEAR);
        return new String(giorno + "/"  + mese + "/" + anno);
    }
}
