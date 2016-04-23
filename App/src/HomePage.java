import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Matteo on 14/03/2016.
 */
public class HomePage extends JFrame {
    private JScrollPane listaScorrimentoTaskAttivi;
    private JPanel panelMain;
    private JTextField barraRicerca;
    private JButton stroicoTask;
    private JButton creaTask;
    private JButton tastoRicerca;
    private JButton inserisciPaziente;
    private JButton generaGrafico1;
    private JButton generaGrafico2;
    private JButton generaGrafico3;
    private JButton generaGrafico4;
    private JButton generaGrafico5;
    private JLabel taskAttivi;
    private JLabel cerca;

    public HomePage(final ContenitoreTask vectorTask){
        super("HomePage");
        setSize(900,600);
        panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(1,2));
        panelMain.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        getContentPane().add(panelMain);

        listaScorrimentoTaskAttivi = new JScrollPane();
        final JPanel tuttiTask = new JPanel();
        tuttiTask.setLayout(new GridLayout(vectorTask.taskDB.size(),1));

        for(Iterator<Task> iteratore = vectorTask.taskDB.iterator(); iteratore.hasNext();){
            Task aux = iteratore.next();

            final JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new GridLayout(1,2));
            final JPanel panelTaskSX = new JPanel();
            panelTaskSX.setLayout(new GridLayout(2,1));
            final JPanel panelTaskDX = new JPanel();
            panelTaskDX.setLayout(new GridLayout(4,1));
            panelTaskSX.add(new JLabel(aux.getTitolo()));
            panelTaskSX.add(new JLabel(aux.getContenuto()));
            panelTaskDX.add(new JLabel(aux.getImportanza()));
            panelTaskDX.add(new JLabel("Termine:  " + App.dataToString(aux.getDataImpostataTermine())));
            panelTaskDX.add(new JLabel(aux.getTipoTask()));
            JButton completato = new JButton("segna come completato");
            completato.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // sposto il task nel vector dei task conclusi
                    tuttiTask.remove(taskPanel);
                    tuttiTask.repaint();
                    listaScorrimentoTaskAttivi.repaint();
                    panelMain.repaint();
                }
            });
            panelTaskDX.add(completato);
            taskPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            taskPanel.setPreferredSize(new Dimension(50,90));
            taskPanel.add(panelTaskSX);
            taskPanel.add(panelTaskDX);

            tuttiTask.add(taskPanel);
        }
        listaScorrimentoTaskAttivi.getViewport().add(tuttiTask);
        listaScorrimentoTaskAttivi.setPreferredSize(new Dimension(400,300));
        panelMain.add(listaScorrimentoTaskAttivi);

        final JPanel panelDX = new JPanel();
        panelDX.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panelDX.setLayout(new GridLayout(8,1));
        panelMain.add(panelDX);

        JPanel ricercaPanel = new JPanel();
        ricercaPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        ricercaPanel.setLayout(new FlowLayout());
            JLabel ricercaLabel = new JLabel("Cerca");
            ricercaPanel.add(ricercaLabel);
            JTextField ricercaText = new JTextField();
            ricercaText.setPreferredSize(new Dimension(300,25));
            ricercaPanel.add(ricercaText);
            JButton ricercaButton = new JButton("O");
            ricercaButton.setPreferredSize(new Dimension(25,23));
            ricercaPanel.add(ricercaButton);
        panelDX.add(ricercaPanel);


        final JPanel creazioneElementi = new JPanel();
        panelDX.add(creazioneElementi);
        creazioneElementi.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        creazioneElementi.setLayout(new GridLayout(2,1));
        creaTask = new JButton("crea nuovo task");
        creaTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // apro una pagina dove inserisco le informazioni in una form per creare un nuovo task utente
                CreaTaskPage taskPage = new CreaTaskPage(vectorTask);
                taskPage.setVisible(true);
                tuttiTask.repaint();
                listaScorrimentoTaskAttivi.repaint();
                panelMain.repaint();
            }
        });
        inserisciPaziente = new JButton("inserisci nuova paziente");
        inserisciPaziente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // apro una pagina dove inserisco le informazioni per inseirire una nuova paziente
            }
        });
        creazioneElementi.add(creaTask);
        creazioneElementi.add(inserisciPaziente);

    }
}
