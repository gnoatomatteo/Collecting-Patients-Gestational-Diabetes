import java.util.GregorianCalendar;

/**
 * Created by Matteo on 10/03/2016.
 */
public class Task {

    /*CAMPI DATI*/
    private int numeroTask;
    private String titolo;
    private String contenuto;
    private String tipoTask; // task creato da utente, task creato per mancanza dati da inserire
    private String importanza; // un task può essere urgente, importante, facoltativo, normale
    private GregorianCalendar dataCreazione;
    private GregorianCalendar dataImpostataTermine;
    private GregorianCalendar dataTerminazione;
    private boolean svolto;

    /*COSTRUTTORE*/
    public Task(String t, String c, String tt, String imp, GregorianCalendar data){
        titolo= t;
        contenuto=c;
        tipoTask= tt;
        importanza=imp;
        dataCreazione = new GregorianCalendar();
        svolto=false;
        dataImpostataTermine = new GregorianCalendar();
        dataImpostataTermine = data;
    }

    /*METOSI SET*/
    public void setNumeroTask(int n){
        numeroTask=n;
    }
    public void setSvolto(boolean b){
        svolto=b;
    }
    public void setTitolo(String t){
        titolo=t;
    }
    public void setContenuto(String c){
        contenuto=c;
    }
    public void setDataCreazione(GregorianCalendar dt){
        dataCreazione = new GregorianCalendar(dt.get(GregorianCalendar.DATE), dt.get(GregorianCalendar.MONTH), dt.get(GregorianCalendar.YEAR));
    }
    public void setDataTerminazione(GregorianCalendar dt){
            dataTerminazione = new GregorianCalendar(dt.get(GregorianCalendar.DATE), dt.get(GregorianCalendar.MONTH), dt.get(GregorianCalendar.YEAR));
    }
    public void setDataImpostataTermine(GregorianCalendar dt){
        dataImpostataTermine = new GregorianCalendar(dt.get(GregorianCalendar.DATE), dt.get(GregorianCalendar.MONTH), dt.get(GregorianCalendar.YEAR));
    }

    /*METODI GET*/
    public int getNumeroTask(){
        return numeroTask;
    }
    public String getTitolo(){
        return titolo;
    }
    public String getContenuto(){
        return contenuto;
    }
    public String getTipoTask(){
        return tipoTask;
    }
    public String getImportanza(){ return importanza;}
    public GregorianCalendar getDataCreazione(){
        return dataCreazione;
    }
    public GregorianCalendar getDataTerminazione(){return dataTerminazione;}
    public GregorianCalendar getDataImpostataTermine(){return dataImpostataTermine;}
    public boolean getSvolto() {
        return svolto;
    }
    public boolean isTerminated(){
        if(dataTerminazione == null) {
            return false;
        }
        return true;
    }
}
