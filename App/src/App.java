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
        Contenitore database = new Contenitore();
        database.load();
        container.load();
        HomePage home = new HomePage(container, database);
        home.setVisible(true);
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
