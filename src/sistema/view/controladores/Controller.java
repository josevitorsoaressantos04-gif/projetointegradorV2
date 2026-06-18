package sistema.view.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void mudarParaLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpLogin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mudarParaCadastro(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpCadastroUsuario.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mudarparaRecuperacao(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpRecuperacao.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void mudarParaMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("erpMenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
