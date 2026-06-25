package sistema.view.controladores;

import javafx.animation.TranslateTransition;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sistema.view.controladores.ConfigController;

import java.io.IOException;

public class MenuController {

    @FXML private Button btnInicio;
    @FXML private Button btnCadastro;
    @FXML private Button btnListas;
    @FXML private Button btnConfig;
    @FXML private StackPane painelDireitoCentral;
    @FXML private SplitPane menuInteiro;
    @FXML private AnchorPane painelEsquerdo;
    @FXML private ImageView imagemLogo;


    @FXML
    public void initialize() {

        SplitPane.setResizableWithParent(painelEsquerdo,false);
        menuInteiro.setDividerPositions(1);


        // Ações de cada botão do painel esquerdo do menu.
        btnInicio.setOnAction(e -> carregarTela("tela_inicio.fxml"));
        btnCadastro.setOnAction(e -> carregarTela("tela_cadastro.fxml"));
        btnListas.setOnAction(e -> carregarTela("tela_listas.fxml"));
        btnConfig.setOnAction(e -> carregarTela("tela_config.fxml"));

        carregarTela("tela_inicio.fxml");

        //Animação de recuo dos botões do menu.
        TranslateTransition recuo = new TranslateTransition(Duration.millis(250), btnInicio);
        TranslateTransition recuo2 = new TranslateTransition(Duration.millis(250), btnCadastro);
        TranslateTransition recuo3 = new TranslateTransition(Duration.millis(250), btnListas);
        TranslateTransition recuo4 = new TranslateTransition(Duration.millis(250), btnConfig);

        if (btnInicio != null) {
            btnInicio.setOnMouseEntered(e-> {
                recuo2.setToX(-70);
                recuo2.play();
                recuo3.setToX(-70);
                recuo3.play();
                recuo4.setToX(-70);
                recuo4.play();
            });
            btnInicio.setOnMouseExited(e->{
                recuo2.setToX(0);
                recuo2.play();
                recuo3.setToX(0);
                recuo3.play();
                recuo4.setToX(0);
                recuo4.play();
            });
        }
        if (btnCadastro != null) {
            btnCadastro.setOnMouseEntered(e-> {
                recuo.setToX(-70);
                recuo.play();
                recuo3.setToX(-70);
                recuo3.play();
                recuo4.setToX(-70);
                recuo4.play();
            });
            btnCadastro.setOnMouseExited(e->{
                recuo.setToX(0);
                recuo.play();
                recuo3.setToX(0);
                recuo3.play();
                recuo4.setToX(0);
                recuo4.play();
            });
        }
        if (btnListas != null) {
            btnListas.setOnMouseEntered(e-> {
                recuo2.setToX(-70);
                recuo2.play();
                recuo.setToX(-70);
                recuo.play();
                recuo4.setToX(-70);
                recuo4.play();
            });
            btnListas.setOnMouseExited(e->{
                recuo2.setToX(0);
                recuo2.play();
                recuo.setToX(0);
                recuo.play();
                recuo4.setToX(0);
                recuo4.play();
            });
        }
        if (btnConfig != null) {
            btnConfig.setOnMouseEntered(e-> {
                recuo2.setToX(-70);
                recuo2.play();
                recuo3.setToX(-70);
                recuo3.play();
                recuo.setToX(-70);
                recuo.play();
            });
            btnConfig.setOnMouseExited(e->{
                recuo2.setToX(0);
                recuo2.play();
                recuo3.setToX(0);
                recuo3.play();
                recuo.setToX(0);
                recuo.play();
            });
        }
    }

    private void carregarTela(String arquivoFxml) {
        try {
            // Carrega o arquivo FXML.
            // Dica: A barra "/" indica que os FXMLs estão na pasta raiz de resources (src/main/resources/)
            Node telaCarregada = FXMLLoader.load(getClass().getResource(arquivoFxml));

            if (telaCarregada instanceof javafx.scene.layout.Region){
                javafx.scene.layout.Region tela = (javafx.scene.layout.Region) telaCarregada;

                tela.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            }

            // Limpa o painel da direita e adiciona a nova tela
            painelDireitoCentral.getChildren().clear();
            painelDireitoCentral.getChildren().add(telaCarregada);

        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela: " + arquivoFxml);
            e.printStackTrace();
        }
    }
}

