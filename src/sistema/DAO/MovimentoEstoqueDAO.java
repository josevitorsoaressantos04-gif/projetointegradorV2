package sistema.DAO;

import sistema.conexao.ConexaoBanco;
import sistema.model.MovimentoEstoque;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentoEstoqueDAO {

    public void registrarEntrada(MovimentoEstoque movimentoEstoque) {
        registrarMovimento(movimentoEstoque, true);
    }

    public void registrarSaida(MovimentoEstoque movimentoEstoque) {
        registrarMovimento(movimentoEstoque, false);
    }

    private void registrarMovimento(MovimentoEstoque movimentoEstoque, boolean entrada) {
        String sqlMovimento = """
                INSERT INTO movimento_estoque (
                    quantidade,
                    data_movimento,
                    observacao,
                    produto_id_produto,
                    usuario_id_usuario,
                    tipo_movimento_id_movimento
                ) VALUES (?, NOW(), ?, ?, ?, ?)
                """;

        String sqlAtualizaEstoqueEntrada = """
                UPDATE produto
                SET estoque = estoque + ?
                WHERE id_produto = ?
                """;

        String sqlAtualizaEstoqueSaida = """
                UPDATE produto
                SET estoque = estoque - ?
                WHERE id_produto = ?
                AND estoque >= ?
                """;

        try (Connection conexao = ConexaoBanco.conectar()) {
            conexao.setAutoCommit(false);

            try (
                    PreparedStatement stmtMovimento = conexao.prepareStatement(sqlMovimento);
                    PreparedStatement stmtEstoque = conexao.prepareStatement(
                            entrada ? sqlAtualizaEstoqueEntrada : sqlAtualizaEstoqueSaida
                    )
            ) {
                stmtEstoque.setInt(1, movimentoEstoque.getQuantidade());
                stmtEstoque.setInt(2, movimentoEstoque.getProdutoIdProduto());

                if (!entrada) {
                    stmtEstoque.setInt(3, movimentoEstoque.getQuantidade());
                }

                int linhasAfetadas = stmtEstoque.executeUpdate();

                if (linhasAfetadas == 0) {
                    conexao.rollback();
                    throw new RuntimeException("Estoque insuficiente ou produto não encontrado.");
                }

                stmtMovimento.setInt(1, movimentoEstoque.getQuantidade());
                stmtMovimento.setString(2, movimentoEstoque.getObservacao());
                stmtMovimento.setInt(3, movimentoEstoque.getProdutoIdProduto());
                stmtMovimento.setInt(4, movimentoEstoque.getUsuarioIdUsuario());
                stmtMovimento.setInt(5, movimentoEstoque.getTipoMovimentoIdMovimento());

                stmtMovimento.executeUpdate();

                conexao.commit();

            } catch (SQLException erro) {
                conexao.rollback();
                throw new RuntimeException("Erro ao registrar movimento de estoque: " + erro.getMessage());
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro de conexão no movimento de estoque: " + erro.getMessage());
        }
    }

    public List<MovimentoEstoque> listar() {
        String sql = """
                SELECT id_movimento, quantidade, data_movimento, observacao,
                       produto_id_produto, usuario_id_usuario, tipo_movimento_id_movimento
                FROM movimento_estoque
                ORDER BY data_movimento DESC
                """;

        List<MovimentoEstoque> movimentos = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                MovimentoEstoque movimento = new MovimentoEstoque();

                movimento.setIdMovimento(rs.getInt("id_movimento"));
                movimento.setQuantidade(rs.getInt("quantidade"));

                if (rs.getTimestamp("data_movimento") != null) {
                    movimento.setDataMovimento(rs.getTimestamp("data_movimento").toLocalDateTime());
                }

                movimento.setObservacao(rs.getString("observacao"));
                movimento.setProdutoIdProduto(rs.getInt("produto_id_produto"));
                movimento.setUsuarioIdUsuario(rs.getInt("usuario_id_usuario"));
                movimento.setTipoMovimentoIdMovimento(rs.getInt("tipo_movimento_id_movimento"));

                movimentos.add(movimento);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar movimentos de estoque: " + erro.getMessage());
        }

        return movimentos;
    }
}