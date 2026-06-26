package sistema.view.controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;
import sistema.controller.LoginController;
import sistema.controller.CdUsuarioCtrl;
import sistema.model.Usuario;

import java.awt.*;
import java.awt.Button;
import java.io.IOException;

public class Controller {

    @FXML private Text textoMudanca;
    @FXML private Text textoMudanca2;
    @FXML private TextField loginCampo;
    @FXML private PasswordField senhaCampo;
    @FXML private TextField campoCadastroNome;
    @FXML private TextField campoCadastroLogin;
    @FXML private PasswordField campoCadastroSenha;
    @FXML private PasswordField campoConfirmaSenha;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private LoginController loginControllerBackend = new LoginController();
    private CdUsuarioCtrl cadastroNovoUsuario = new CdUsuarioCtrl();

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

    @FXML
    public void fazerLogin(Event event) {
        String login = loginCampo.getText();
        String senha = senhaCampo.getText();

        try {
            // Tenta autenticar. Se der erro, o Service lança uma Exception e cai no catch
            Usuario usuarioLogado = loginControllerBackend.realizarLogin(login, senha);

            // Se chegou nesta linha, o login foi um sucesso! Redireciona para o Menu.
            mudarParaMenu(event);

        } catch (RuntimeException e) {
            // Captura as exceções do LoginService ("Informe o login", "Login inválido", etc)
            exibirAlerta("Erro de Autenticação", e.getMessage(), Alert.AlertType.WARNING);
        } catch (IOException e) {
            exibirAlerta("Erro de Sistema", "Não foi possível carregar a tela de Menu.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void exibirAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    @FXML
    public void fazerCadastro (Event event) {
        String nome = campoCadastroNome.getText();
        String login = campoCadastroLogin.getText();
        String senha = campoCadastroSenha.getText();
        String confirmaSenha = campoConfirmaSenha.getText();

        if (!senha.equals(confirmaSenha)){
            exibirAlerta("Erro de validação.", "As senhas digitadas não coincidem. Tente novamente!", Alert.AlertType.WARNING);
            return;
        }
        try {
            cadastroNovoUsuario.cadastrarUsuario(nome, login, senha);
            exibirAlerta("Sucesso!","Novo usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);

        } catch (RuntimeException e) {
            // Captura as exceções do seu UsuarioService (Ex: "Informe um nome", "Limite máximo")
            exibirAlerta("Aviso", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            // Captura erros gerais (como problema de conexão com o banco)
            exibirAlerta("Erro de Sistema", "Ocorreu um erro interno: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
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
