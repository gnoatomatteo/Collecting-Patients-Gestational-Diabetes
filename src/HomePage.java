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
        setLayout(new FlowLayout());
        listaScorrimentoTaskAttivi = createScrollAreaTask(vectorTask);
        listaScorrimentoTaskAttivi.setBackground(new Color(0, 0, 0));
        listaScorrimentoTaskAttivi.setMaximumSize(new Dimension(450,290));
        listaScorrimentoTaskAttivi.setMinimumSize(new Dimension(450, 290));
        add(listaScorrimentoTaskAttivi);

    }

    private JScrollPane createScrollAreaTask(Vector<Task> vectorTask){
        JPanel contentTask = new JPanel();
        contentTask.setLayout(new GridLayout(vectorTask.size(),1));
        JScrollPane contentScrollTask = new JScrollPane(contentTask);
        contentScrollTask.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        contentScrollTask.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        for(Iterator<Task> iteratore = vectorTask.iterator(); iteratore.hasNext();){
            Task aux = iteratore.next();

            JPanel taskPanel = new JPanel();
            taskPanel.setLayout(new GridLayout(2,2));
            taskPanel.add(new JLabel(aux.getTitolo()));
            taskPanel.add(new JLabel(aux.getContenuto()));
            taskPanel.add(new JLabel(aux.getImportanza()));
            taskPanel.add(new JLabel(aux.getTipoTask()));
            taskPanel.setBorder(BorderFactory.createLineBorder(Color.black));

            contentTask.add(taskPanel);
        }
        return contentScrollTask;


    }


}
