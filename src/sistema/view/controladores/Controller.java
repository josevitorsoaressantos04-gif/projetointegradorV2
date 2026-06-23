package sistema.view.controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

public class Controller {

    @FXML private Text textoMudanca;
    @FXML private Text textoMudanca2;


    private Stage stage;
    private Scene scene;
    private Parent root;
    //@FXML private Button btnCodigo;

    @FXML public void initialize() {


        Glow brilho = new Glow(0.4);
        Glow brilhodesativado = new Glow(0.0);

        if (textoMudanca != null) {
            textoMudanca.setOnMouseEntered(e -> {
                textoMudanca.setStyle("-fx-underline: true");
                textoMudanca.setEffect(brilho);
            });
            textoMudanca.setOnMouseExited(e -> {
                textoMudanca.setStyle("-fx-underline: false");
                textoMudanca.setEffect(brilhodesativado);
            });
        }
        if (textoMudanca2 != null) {
            textoMudanca2.setOnMouseEntered(e -> {
                textoMudanca2.setStyle("-fx-underline: true");
                textoMudanca2.setEffect(brilho);
            });
            textoMudanca2.setOnMouseExited(e -> {
                textoMudanca2.setStyle("-fx-underline: false");
                textoMudanca2.setEffect(brilhodesativado);
            });
        }
    }
    public void mudarParaLogin(Event event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login de usuário");
        stage.show();
    }

    public void mudarParaCadastro(Event event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpCadastroUsuario.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cadastrar usuário");
        stage.show();
    }

    public void mudarparaRecuperacao(Event event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpRecuperacao.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Recuperação de cadastro");
        stage.show();
    }

    public void gerarCodigoRecuperacao(Event event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpCodigo.fxml"));
        Stage popCodigo = new Stage();
        popCodigo.setTitle("");
        popCodigo.setScene(new Scene(root));
        popCodigo.setResizable(false);
        popCodigo.initModality(Modality.APPLICATION_MODAL);
        popCodigo.showAndWait();

    }



    public void mudarParaMenu(Event event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setFullScreen(true);
        stage.show();
    }

}
