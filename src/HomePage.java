import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Matteo on 14/03/2016.
 */
public class HomePage extends JFrame {
    private JScrollPane listaScorrimentoTaskAttivi;
    private JPanel panelTask;
    private JButton stroicoTask;
    private JButton creaTask;
    private JButton tastoRicerca;
    private JButton inserisciPazientte;
    private JButton generaGrafico1;
    private JButton generaGrafico2;
    private JButton generaGrafico3;
    private JButton generaGrafico4;
    private JButton generaGrafico5;
    private JLabel taskAttivi;
    private JLabel cerca;

    public HomePage(Vector<Task> vectorTask){
        super("HomePage");
        setSize(900,600);
        panelTask = new JPanel();
        panelTask.setLayout(new BorderLayout());
        getContentPane().add(panelTask);

        listaScorrimentoTaskAttivi = new JScrollPane();
        JPanel tuttiTask = new JPanel();
        tuttiTask.setLayout(new GridLayout(vectorTask.size(),1));

        for(Iterator<Task> iteratore = vectorTask.iterator(); iteratore.hasNext();){
            Task aux = iteratore.next();

            JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new GridLayout(3,2));
            taskPanel.add(new JLabel(aux.getTitolo()));
            taskPanel.add(new JLabel(aux.getContenuto()));
            taskPanel.add(new JLabel(aux.getImportanza()));
            taskPanel.add(new JLabel(aux.getTipoTask()));
            JButton completato = new JButton("completato");
            taskPanel.add(new JButton("completato"));
            taskPanel.setBorder(BorderFactory.createLineBorder(Color.black));
            taskPanel.setPreferredSize(new Dimension(50,90));

            tuttiTask.add(taskPanel);
        }
        listaScorrimentoTaskAttivi.getViewport().add(tuttiTask);
        listaScorrimentoTaskAttivi.setPreferredSize(new Dimension(400,300));
        panelTask.add(listaScorrimentoTaskAttivi, BorderLayout.WEST);
        panelTask.setPreferredSize(new Dimension(400,300));

    }


}
