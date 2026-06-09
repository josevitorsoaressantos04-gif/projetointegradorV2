package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListagemProdutoView extends JFrame {

    public ListagemProdutoView() {
        setTitle("Listagem de Produtos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Listagem de Produtos");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}