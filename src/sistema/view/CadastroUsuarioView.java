package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroUsuarioView extends JFrame {

    public CadastroUsuarioView() {
        setTitle("Cadastro de Usuario");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Cadastro de Usuario");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}