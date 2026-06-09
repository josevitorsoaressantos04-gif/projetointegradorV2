package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class LogsView extends JFrame {

    public LogsView() {
        setTitle("Logs do Sistema");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Logs do Sistema");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}