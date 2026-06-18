package sistema;

import sistema.view.LoginView;
import sistema.view.RecuperacaoSenhaView;

public class Main {

    public static void main(String[] args) {
        RecuperacaoSenhaView tela = new RecuperacaoSenhaView();
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
    }
}