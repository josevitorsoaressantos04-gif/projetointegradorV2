package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.FormaPagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {

    public void inserir(FormaPagamento formaPagamento) {
        String sql = """
                INSERT INTO forma_pagamento (
                    descricao
                ) VALUES (?)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, formaPagamento.getDescricao());
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir forma de pagamento: " + erro.getMessage());
        }
    }

    public List<FormaPagamento> listar() {
        String sql = """
                SELECT id, descricao
                FROM forma_pagamento
                ORDER BY id
                """;

        List<FormaPagamento> formas = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                FormaPagamento forma = new FormaPagamento();

                forma.setIdPagamento(rs.getInt("id"));
                forma.setDescricao(rs.getString("descricao"));

                formas.add(forma);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar formas de pagamento: " + erro.getMessage());
        }

        return formas;
    }
}