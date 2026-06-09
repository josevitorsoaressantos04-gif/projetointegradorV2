package sistema.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class CadastroVendaView extends JFrame {

    public CadastroVendaView() {
        setTitle("Cadastro de Vendas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Cadastro de Vendas");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}