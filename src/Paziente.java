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
    private boolean dietaSeguita;
    private String tipologiaDiabete; // obbligatorio
    private String modalitaParto;
    private int pesoBambino;
    private String notePersonali;

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

    /*METODI FUNZIONALI*/
    public float aumentoPonderale(){
       return pesoFineGravidanza-pesoInizioGravidanza;
    }
}
