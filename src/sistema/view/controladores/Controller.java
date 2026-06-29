package sistema.view.controladores;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.effect.Glow;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;
import sistema.controller.LoginController;
import sistema.controller.CdUsuarioCtrl;
import sistema.controller.RecuperacaoSenhaController;
import sistema.model.Usuario;

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
    @FXML private TextField recuperacaoLogin;
    @FXML private TextField recuperacaoCodigo;
    @FXML private TextField recuperacaoNovaSenha;
    @FXML private TextField recuperacaoConfirmaSenha;

    @FXML private TextField recuperacaoCodigoGerado;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private LoginController loginControllerBackend = new LoginController();
    private CdUsuarioCtrl cadastroNovoUsuario = new CdUsuarioCtrl();
    private final RecuperacaoSenhaController recuperacaoCtrl = new RecuperacaoSenhaController();

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

    public void mostrarCodigoRecuperacao(Event event) throws IOException {
        String login = recuperacaoLogin.getText();

        try {
            // 1. Gera o código seguro vindo do Service
            String codigoGerado = recuperacaoCtrl.gerarCodigoRecuperacao(login);

            // 2. Carrega a janela do pop-up
            FXMLLoader loader = new FXMLLoader(getClass().getResource("erpCodigo.fxml"));
            root = loader.load();

            // 3. Guarda o código gerado dentro do root (Passagem de dados entre janelas)
            Controller popupController = loader.getController();

            //4
            popupController.setCodigoNoPopUp(codigoGerado);
            recuperacaoCodigo.setText(codigoGerado);

        Stage popCodigo = new Stage();
        popCodigo.setTitle("");
        popCodigo.setScene(new Scene(root));
        popCodigo.setResizable(false);
        popCodigo.initModality(Modality.APPLICATION_MODAL);
        popCodigo.showAndWait();

    } catch (RuntimeException e) {
        // Captura se o login estiver em branco ou se acontecer outra validação de regra de negócio
        exibirAlerta("Aviso", e.getMessage(), Alert.AlertType.WARNING);
    } catch (IOException e) {
        exibirAlerta("Erro de Sistema", "Não foi possível abrir o arquivo erpCodigo.fxml.", Alert.AlertType.ERROR);
        e.printStackTrace();
    }
}
    public void setCodigoNoPopUp(String codigo) {
        if (recuperacaoCodigoGerado != null) {
            recuperacaoCodigoGerado.setText(codigo);
        }
    }
    @FXML
    public void salvarRecuperacao(Event event) {
        String codigo = recuperacaoCodigo.getText();
        String novaSenha = recuperacaoNovaSenha.getText();
        String confirmaSenha = recuperacaoConfirmaSenha.getText();

        try {
            // Executa a alteração chamando o backend
            recuperacaoCtrl.alterarSenhaComCodigo(codigo, novaSenha, confirmaSenha);

            exibirAlerta("Sucesso", "A sua senha foi redefinida com sucesso!", Alert.AlertType.INFORMATION);

            // Limpa o formulário após salvar
            limparCamposRecuperacao(event);

        } catch (RuntimeException e) {
            // Trata senhas que não coincidem, tamanho inválido ou código incorreto
            exibirAlerta("Validação", e.getMessage(), Alert.AlertType.WARNING);
        } catch (Exception e) {
            exibirAlerta("Erro de Sistema", "Ocorreu um erro ao atualizar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void limparCamposRecuperacao(Event event) {
        if (recuperacaoLogin != null) recuperacaoLogin.clear();
        if (recuperacaoCodigo != null) recuperacaoCodigo.clear();
        if (recuperacaoNovaSenha != null) recuperacaoNovaSenha.clear();
        if (recuperacaoConfirmaSenha != null) recuperacaoConfirmaSenha.clear();
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
