package sistema.view.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CadastroController {

    @FXML private Button btnCliente;
    @FXML private Button btnProduto;
    @FXML private Button btnVendas;

    @FXML private GridPane formCliente;
    @FXML private GridPane formProduto;
    @FXML private GridPane formVendas;

    @FXML
    public void initialize() {
        // Define as ações dos botões superiores
        btnCliente.setOnAction(e -> mostrarFormulario(formCliente));
        btnProduto.setOnAction(e -> mostrarFormulario(formProduto));
        btnVendas.setOnAction(e -> mostrarFormulario(formVendas));
    }

    private void mostrarFormulario(GridPane formularioSelecionado) {
        // Oculta todos
        formCliente.setVisible(false);
        formProduto.setVisible(false);
        formVendas.setVisible(false);

        // Exibe apenas o selecionado
        formularioSelecionado.setVisible(true);
    }
}
