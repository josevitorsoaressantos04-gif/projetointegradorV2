package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListagemUsuarioView extends JFrame {

    public ListagemUsuarioView() {
        setTitle("Listagem de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Listagem de Usuarios");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}