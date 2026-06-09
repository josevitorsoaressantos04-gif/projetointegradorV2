package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListagemPessoaView extends JFrame {

    public ListagemPessoaView() {
        setTitle("Listagem de Pessoas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Listagem de Pessoas");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}