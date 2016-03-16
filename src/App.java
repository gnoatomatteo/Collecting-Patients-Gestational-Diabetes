import javax.swing.*;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 12/03/2016.
 */



public class App {

    public static String dataToString(GregorianCalendar data){
        Integer giorno = data.get(GregorianCalendar.DATE);
        Integer mese = data.get(GregorianCalendar.MONTH)+1;
        Integer anno = data.get(GregorianCalendar.YEAR);
        return new String(giorno.toString() + "/"  + mese.toString() + "/" + anno.toString());
    }

    public static void main(String[] args) {
        ContenitoreTask container = new ContenitoreTask();
        for(int i=0; i < 1 ; ++i) {
            Task task = new Task("titolo", "contenuto", "tipoTask", "importanza", new GregorianCalendar(05,05,2020) );
            container.addTask(task);
        }

        Contenitore database = new Contenitore();
        GregorianCalendar dataCorrente = new GregorianCalendar();
        for(int i=0; i < 1 ; i++){
            Paziente paziente = new Paziente("nome","cognome", dataCorrente, "nazionalita", 50.6f , "tipologiaDiabete");
            paziente.setTelefono("049929235");

            database.addPaziente(paziente);
        }
        database.save();
        database.load();

        HomePage home = new HomePage(container.taskDB);
        home.setVisible(true);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
