import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * Created by Matteo on 16/03/2016.
 */
public class CreaTaskPage extends JFrame {
    private String titolo;
    private String contenuto;
    private String importanza;
    private GregorianCalendar dataImpostataTermine;
    private JButton aggiungi;
    private JButton annulla;

    public CreaTaskPage(final ContenitoreTask vectorTask){
        super("Crea task");
        super.setPreferredSize(new Dimension(280, 450));
        setSize(new Dimension(280,450));
        setResizable(false);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        getContentPane().add(mainPanel);

        JLabel titoloLabel = new JLabel("titolo");
        titoloLabel.setBounds(10,10,80,25);
        mainPanel.add(titoloLabel);

        final JTextField titoloText = new JTextField();
        titoloText.setBounds(100,10,160,25);
        mainPanel.add(titoloText);

        JLabel contenutoLabel = new JLabel("contenuto");
        contenutoLabel.setBounds(10,40,80,25);
        mainPanel.add(contenutoLabel);

        final JTextArea contenutoText = new JTextArea();
        contenutoText.setBounds(100,40,160,100);
        mainPanel.add(contenutoText);

        JLabel importanzaLabel = new JLabel("importanza");
        importanzaLabel.setBounds(10, 180, 80, 25);
        mainPanel.add(importanzaLabel);

        String[] importanzaSelect = {"Urgente","Attenzione","Rinviabile"};
        final JComboBox<String> importanzaText = new JComboBox<>(importanzaSelect);
        importanzaText.setBounds(100,180,80,25);
        mainPanel.add(importanzaText);

        JLabel dataTermineLabel = new JLabel("termine:");
        dataTermineLabel.setBounds(10,220, 80,25);
        mainPanel.add(dataTermineLabel);



            JLabel giornoTemineLabel = new JLabel("giorno");
            giornoTemineLabel.setBounds(10,240,80,25);
            mainPanel.add(giornoTemineLabel);

            String[] giornoSelect = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
            final JComboBox<String> giornoText = new JComboBox<>(giornoSelect);
            giornoText.setBounds(100,240,80,25);
            mainPanel.add(giornoText);


            JLabel meseTermineLabel = new JLabel("mese");
            meseTermineLabel.setBounds(10,260,80,25);
            mainPanel.add(meseTermineLabel);

            String[] meseSelect = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
            final JComboBox<String> meseText = new JComboBox<>(meseSelect);
            meseText.setBounds(100,260,80,25);
            mainPanel.add(meseText);

            JLabel annoTermineLabel = new JLabel("anno");
            annoTermineLabel.setBounds(10,280,80,25);
            mainPanel.add(annoTermineLabel);

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
            annoText.setBounds(100,280,80,25);
            mainPanel.add(annoText);

            aggiungi = new JButton("aggiungi");
            annulla = new JButton("annulla");

            aggiungi.setBounds(10, 350, 100, 25);
            mainPanel.add(aggiungi);
            annulla.setBounds(130,350,100,25);
            mainPanel.add(annulla);

            aggiungi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String titolo = titoloText.getText();
                    System.out.println(titolo);
                    if(!(titolo.isEmpty())) {
                        String contenuto = contenutoText.getText();
                        String tipoTask = "task utente";
                        String importanza = (String) importanzaText.getSelectedItem();
                        // recupero l'anno
                        String annoString = (String) annoText.getSelectedItem();
                        Integer annoInteger = new Integer(annoString);
                        // recupero mese
                        String meseString = (String) meseText.getSelectedItem();
                        int meseInt = 0;
                        if (meseString == "Gennaio") {
                            meseInt = 1;
                        } else if (meseString == "Febbraio") {
                            meseInt = 2;
                        } else if (meseString == "Marzo") {
                            meseInt = 3;
                        } else if (meseString == "Aprile") {
                            meseInt = 4;
                        } else if (meseString == "maggio") {
                            meseInt = 5;
                        } else if (meseString == "Giugno") {
                            meseInt = 6;
                        } else if (meseString == "Luglio") {
                            meseInt = 7;
                        } else if (meseString == "Agosto") {
                            meseInt = 8;
                        } else if (meseString == "Settembre") {
                            meseInt = 9;
                        } else if (meseString == "Ottobre") {
                            meseInt = 10;
                        } else if (meseString == "Novembre") {
                            meseInt = 11;
                        } else if (meseString == "Dicembre") {
                            meseInt = 12;
                        }
                        //recupero giorno
                        String giornoString = (String) giornoText.getSelectedItem();
                        Integer giornoInteger = new Integer(giornoString);
                        GregorianCalendar dataInizio = new GregorianCalendar(annoInteger.intValue(), meseInt, giornoInteger.intValue());
                        Task nuovo = new Task(titolo, contenuto, tipoTask, importanza, dataInizio);
                        vectorTask.addTask(nuovo);
                        Container frame = aggiungi.getParent();
                        do
                            frame = frame.getParent();
                        while (!(frame instanceof JFrame));
                        ((JFrame) frame).dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(new Frame(),
                                "ATTENZIONE: inserire almeno un titolo per aggiungere un nuovo task",
                                "Campi obbligatori non compilati",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
            annulla.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    Container frame = annulla.getParent();
                    do
                        frame = frame.getParent();
                    while (!(frame instanceof JFrame));
                    ((JFrame) frame).dispose();
                }
            });
    }
}
