package sistema.view.controladores;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import java.io.IOException;

public class MenuController {

    @FXML private Button btnInicio;
    @FXML private Button btnCadastro;
    @FXML private Button btnListas;
    @FXML private Button btnConfig;
    @FXML private StackPane painelDireitoCentral;
    @FXML private Text bemVindo;

    @FXML
    public void initialize() {
        // Define as ações dos botões laterais
        btnInicio.setOnAction(e -> carregarTela("tela_inicio.fxml"));
        btnCadastro.setOnAction(e -> carregarTela("tela_cadastro.fxml"));
        btnListas.setOnAction(e -> carregarTela("tela_listas.fxml"));
        btnConfig.setOnAction(e -> carregarTela("tela_config.fxml"));

        // Carrega a tela de Início assim que o app abre
        carregarTela("tela_inicio.fxml");

        //bemVindo.setText("Bem vindo, " + usuarioLogado.getNome());
    }

    private void carregarTela(String arquivoFxml) {
        try {
            // Carrega o arquivo FXML.
            // Dica: A barra "/" indica que os FXMLs estão na pasta raiz de resources (src/main/resources/)
            Node telaCarregada = FXMLLoader.load(getClass().getResource(arquivoFxml));

            // Limpa o painel da direita e adiciona a nova tela
            painelDireitoCentral.getChildren().clear();
            painelDireitoCentral.getChildren().add(telaCarregada);

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + arquivoFxml);
            e.printStackTrace();
        }
    }
}

