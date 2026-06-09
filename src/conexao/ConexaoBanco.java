package conexao;

import java.sql.*;

public class ConexaoBanco {
    private static final String url = "jdbc:mysql://localhost:3306/erp_sistema";
    private static final String usuario = "root";
    private static final String senha = "";

    // metodo que conecta com o banco de dados
    public static Connection conectar() {

        // tentativa de conexao e retorna no terminal que a conexao foi realizada
        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!");
            return conexao;

            // caso a conexao falhe o sistema retorna uma mensagem de erro, com o SQLException erro e sai com os
            // comandos prints abaixo
        } catch (SQLException erro) {
            System.out.println("Erro ao conectar ao banco de dados:");
            System.out.println(erro.getMessage());
            return null;
        }
    }

}

