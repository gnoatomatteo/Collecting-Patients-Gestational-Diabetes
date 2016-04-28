import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by break_000 on 28/04/2016.
 */
public class ListaRisultatiPage extends JFrame {
    public ListaRisultatiPage(Vector<Paziente> result){
        super("Pazienti trovati");
        super.setPreferredSize(new Dimension(300,400));
        setSize(new Dimension(300,400));
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        final JList<Paziente> listaRisultati = new JList<>(result);
        topPanel.add(listaRisultati, BorderLayout.CENTER);

        JButton visualizza = new JButton("visualizza");
        topPanel.add(visualizza,BorderLayout.SOUTH);

        visualizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paziente aux = listaRisultati.getSelectedValue();
                InfoPazientePage page = new InfoPazientePage(aux);
                page.setVisible(true);
            }
        });

    }
}
