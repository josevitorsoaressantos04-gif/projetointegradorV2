package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroPessoaView extends JFrame {

    public CadastroPessoaView() {
        setTitle("Cadastro de Pessoas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Cadastro de Pessoas");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}