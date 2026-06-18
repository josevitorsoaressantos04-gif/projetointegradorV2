package sistema;

// import sistema.view.LoginView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/controladores/erpLogin.fxml"));
        primaryStage.setTitle("Login de usuário");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    /*
    public static void main(String[] args) {
    }
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
    */
}