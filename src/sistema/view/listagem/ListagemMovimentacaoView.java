package sistema.view.listagem;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListagemMovimentacaoView extends JFrame {

    public ListagemMovimentacaoView() {
        setTitle("Listagem de Movimentacoes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Listagem de Movimentacoes");
        titulo.setBounds(30, 30, 350, 25);
        add(titulo);
    }
}