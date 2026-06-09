package sistema.view.listagem;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ListagemVendaView extends JFrame {

    public ListagemVendaView() {
        setTitle("Listagem de Vendas");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("Tela de Listagem de Vendas");
        titulo.setBounds(30, 30, 300, 25);
        add(titulo);
    }
}