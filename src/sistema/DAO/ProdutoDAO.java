package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserir(Produto produto) {
        String sql = """
                INSERT INTO produto (
                    nome,
                    descricao,
                    valor_custo,
                    valor_venda,
                    estoque
                ) VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getValorCusto());
            stmt.setBigDecimal(4, produto.getValorVenda());
            stmt.setInt(5, produto.getEstoque());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao inserir produto: " + erro.getMessage());
        }
    }

    public List<Produto> listar() {
        String sql = """
                SELECT id_produto, nome, descricao, valor_custo, valor_venda, estoque
                FROM produto
                ORDER BY nome
                """;

        List<Produto> produtos = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Produto produto = new Produto();

                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValorCusto(rs.getBigDecimal("valor_custo"));
                produto.setValorVenda(rs.getBigDecimal("valor_venda"));
                produto.setEstoque(rs.getInt("estoque"));

                produtos.add(produto);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar produtos: " + erro.getMessage());
        }

        return produtos;
    }

    public Produto buscarPorId(int idProduto) {
        String sql = """
                SELECT id_produto, nome, descricao, valor_custo, valor_venda, estoque
                FROM produto
                WHERE id_produto = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, idProduto);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();

                produto.setIdProduto(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValorCusto(rs.getBigDecimal("valor_custo"));
                produto.setValorVenda(rs.getBigDecimal("valor_venda"));
                produto.setEstoque(rs.getInt("estoque"));

                return produto;
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar produto: " + erro.getMessage());
        }

        return null;
    }

    public void atualizar(Produto produto) {
        String sql = """
                UPDATE produto
                SET nome = ?,
                    descricao = ?,
                    valor_custo = ?,
                    valor_venda = ?,
                    estoque = ?
                WHERE id_produto = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getValorCusto());
            stmt.setBigDecimal(4, produto.getValorVenda());
            stmt.setInt(5, produto.getEstoque());
            stmt.setInt(6, produto.getIdProduto());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar produto: " + erro.getMessage());
        }
    }

    public void excluir(int idProduto) {
        String sql = """
                DELETE FROM produto
                WHERE id_produto = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir produto: " + erro.getMessage());
        }
    }

    public void atualizarEstoque(int idProduto, int novoEstoque) {
        String sql = """
                UPDATE produto
                SET estoque = ?
                WHERE id_produto = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, novoEstoque);
            stmt.setInt(2, idProduto);

            stmt.executeUpdate();

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar estoque: " + erro.getMessage());
        }
    }
}