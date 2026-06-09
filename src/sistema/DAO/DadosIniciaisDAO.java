package sistema.DAO;


import sistema.conexao.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DadosIniciaisDAO {

    public void inserirDadosIniciais() {
        inserirUsuarioAdministrador();
        inserirFormasPagamento();
        inserirTiposMovimento();
        inserirStatus();
    }

    private void inserirUsuarioAdministrador() {
        String sql = """
                INSERT INTO usuario (
                    id_usuario,
                    nome,
                    login,
                    senha,
                    data_cadastro
                ) VALUES (?, ?, ?, ?, NOW())
                ON DUPLICATE KEY UPDATE
                    nome = VALUES(nome),
                    login = VALUES(login),
                    senha = VALUES(senha)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Administrador");
            stmt.setString(3, "admin");
            stmt.setString(4, "123456");

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir usuário administrador: " + erro.getMessage());
        }
    }

    private void inserirFormasPagamento() {
        String sql = """
                INSERT INTO forma_pagamento (
                    id_pagamento,
                    descricao
                ) VALUES (?, ?)
                ON DUPLICATE KEY UPDATE
                    descricao = VALUES(descricao)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Dinheiro");
            stmt.executeUpdate();

            stmt.setInt(1, 2);
            stmt.setString(2, "Pix");
            stmt.executeUpdate();

            stmt.setInt(1, 3);
            stmt.setString(2, "Cartão de Crédito");
            stmt.executeUpdate();

            stmt.setInt(1, 4);
            stmt.setString(2, "Cartão de Débito");
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir formas de pagamento: " + erro.getMessage());
        }
    }

    private void inserirTiposMovimento() {
        String sql = """
                INSERT INTO tipo_movimento (
                    id_movimento,
                    descricao
                ) VALUES (?, ?)
                ON DUPLICATE KEY UPDATE
                    descricao = VALUES(descricao)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Entrada");
            stmt.executeUpdate();

            stmt.setInt(1, 2);
            stmt.setString(2, "Saída");
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir tipos de movimento: " + erro.getMessage());
        }
    }

    private void inserirStatus() {
        String sql = """
                INSERT INTO `status` (
                    id_status,
                    descricao
                ) VALUES (?, ?)
                ON DUPLICATE KEY UPDATE
                    descricao = VALUES(descricao)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, 1);
            stmt.setString(2, "Finalizada");
            stmt.executeUpdate();

            stmt.setInt(1, 2);
            stmt.setString(2, "Cancelada");
            stmt.executeUpdate();

            stmt.setInt(1, 3);
            stmt.setString(2, "Aberta");
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir status: " + erro.getMessage());
        }
    }
}