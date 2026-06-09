package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroProdutoView extends JFrame {

    public CadastroProdutoView() {
        setTitle("Cadastro de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Cadastro de Produtos");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}