package sistema.view.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

public class Controller {

    @FXML private Text retornarLogin;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    //@FXML private Button btnCodigo;

    public void mudarParaLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login de usuário");
        stage.show();
    }

    public void mudarParaCadastro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpCadastroUsuario.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cadastrar usuário");
        stage.show();
    }

    public void mudarparaRecuperacao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpRecuperacao.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Recuperação de cadastro");
        stage.show();
    }

    public void gerarCodigoRecuperacao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpCodigo.fxml"));
        Stage popCodigo = new Stage();
        popCodigo.setTitle("");
        popCodigo.setScene(new Scene(root));
        popCodigo.setResizable(false);
        popCodigo.initModality(Modality.APPLICATION_MODAL);
        popCodigo.showAndWait();

    }



    public void mudarParaMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Menu");
        stage.setFullScreen(true);
        stage.show();
    }

}
