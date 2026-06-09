package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroMovimentacaoView extends JFrame {

    public CadastroMovimentacaoView() {
        setTitle("Cadastro de Movimentacoes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Cadastro de Movimentacoes");
        titulo.setBounds(30, 30, 350, 25);
        add(titulo);
    }
}