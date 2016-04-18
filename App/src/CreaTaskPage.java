import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 16/03/2016.
 */
public class CreaTaskPage extends JFrame {
    private String titolo;
    private String contenuto;
    private String importanza;
    private GregorianCalendar dataImpostataTermine;

    public CreaTaskPage(){
        super("Crea task");
        super.setPreferredSize(new Dimension(300, 550));
        setSize(new Dimension(300,550));
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel titoloLabel = new JLabel("titolo");
        titoloLabel.setBounds(10,10,80,25);
        mainPanel.add(titoloLabel);

        JTextField titoloText = new JTextField();
        titoloText.setBounds(100,10,160,25);
        mainPanel.add(titoloText);

        JLabel contenutoLabel = new JLabel("contenuto");
        contenutoLabel.setBounds(10,40,80,25);
        mainPanel.add(contenutoLabel);

        JTextArea contenutoText = new JTextArea();
        contenutoText.setBounds(100,40,160,100);
        mainPanel.add(contenutoText);

        JLabel importanzaLabel = new JLabel("importanza");
        importanzaLabel.setBounds(10, 180, 80, 25);
        mainPanel.add(importanzaLabel);

        String[] importanzaSelect = {"Urgente","Attenzione","Rinviabile"};
        JComboBox<String> importanzaText = new JComboBox<>(importanzaSelect);
        importanzaText.setBounds(100,180,80,25);
        mainPanel.add(importanzaText);

        JLabel dataTermineLabel = new JLabel("termine:");
        dataTermineLabel.setBounds(10,220, 80,25);
        mainPanel.add(dataTermineLabel);



            JLabel giornoTemineLabel = new JLabel("giorno");
            giornoTemineLabel.setBounds(10,240,80,25);
            mainPanel.add(giornoTemineLabel);


            JLabel meseTermineLabel = new JLabel("mese");
            meseTermineLabel.setBounds(10,260,80,25);
            mainPanel.add(meseTermineLabel);

            JLabel annoTermineLabel = new JLabel("anno");
            annoTermineLabel.setBounds(10,280,80,25);
            mainPanel.add(annoTermineLabel);




    }
}
