package sistema.DAO;


import sistema.conexao.ConexaoBanco;
import sistema.model.ItemVenda;
import sistema.model.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public int inserirVendaComItem(Venda venda, ItemVenda itemVenda) {
        String sqlVenda = """
                INSERT INTO venda (
                    data_venda,
                    valor_total,
                    desconto,
                    usuario_id_usuario,
                    cliente_id_cliente,
                    forma_pagamento_id_pagamento,
                    status_id_status
                ) VALUES (NOW(), ?, ?, ?, ?, ?, ?)
                """;

        String sqlItem = """
                INSERT INTO item_venda (
                    quantidade,
                    valor_unitario,
                    valor_total,
                    venda_id_venda,
                    produto_id_produto,
                    venda_id_venda1
                ) VALUES (?, ?, ?, ?, ?, ?)
                """;

        String sqlBaixarEstoque = """
                UPDATE produto
                SET estoque = estoque - ?
                WHERE id_produto = ?
                AND estoque >= ?
                """;

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

        try (Connection conexao = ConexaoBanco.conectar()) {
            conexao.setAutoCommit(false);

            try (
                    PreparedStatement stmtVenda = conexao.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement stmtItem = conexao.prepareStatement(sqlItem);
                    PreparedStatement stmtEstoque = conexao.prepareStatement(sqlBaixarEstoque);
                    PreparedStatement stmtMovimento = conexao.prepareStatement(sqlMovimento)
            ) {
                stmtVenda.setBigDecimal(1, venda.getValorTotal());
                stmtVenda.setBigDecimal(2, venda.getDesconto());
                stmtVenda.setInt(3, venda.getUsuarioIdUsuario());
                stmtVenda.setInt(4, venda.getClienteIdCliente());
                stmtVenda.setInt(5, venda.getFormaPagamentoIdPagamento());
                stmtVenda.setInt(6, venda.getStatusIdStatus());

                stmtVenda.executeUpdate();

                ResultSet rs = stmtVenda.getGeneratedKeys();

                if (!rs.next()) {
                    conexao.rollback();
                    throw new RuntimeException("Não foi possível recuperar o ID da venda.");
                }

                int idVenda = rs.getInt(1);

                stmtEstoque.setInt(1, itemVenda.getQuantidade());
                stmtEstoque.setInt(2, itemVenda.getProdutoIdProduto());
                stmtEstoque.setInt(3, itemVenda.getQuantidade());

                int linhasEstoque = stmtEstoque.executeUpdate();

                if (linhasEstoque == 0) {
                    conexao.rollback();
                    throw new RuntimeException("Estoque insuficiente para finalizar a venda.");
                }

                stmtItem.setInt(1, itemVenda.getQuantidade());
                stmtItem.setBigDecimal(2, itemVenda.getValorUnitario());
                stmtItem.setBigDecimal(3, itemVenda.getValorTotal());
                stmtItem.setInt(4, idVenda);
                stmtItem.setInt(5, itemVenda.getProdutoIdProduto());
                stmtItem.setInt(6, idVenda);

                stmtItem.executeUpdate();

                stmtMovimento.setInt(1, itemVenda.getQuantidade());
                stmtMovimento.setString(2, "Saída gerada pela venda ID: " + idVenda);
                stmtMovimento.setInt(3, itemVenda.getProdutoIdProduto());
                stmtMovimento.setInt(4, venda.getUsuarioIdUsuario());
                stmtMovimento.setInt(5, 2);

                stmtMovimento.executeUpdate();

                conexao.commit();

                return idVenda;

            } catch (SQLException erro) {
                conexao.rollback();
                throw new RuntimeException("Erro ao registrar venda: " + erro.getMessage());
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro de conexão ao registrar venda: " + erro.getMessage());
        }
    }

    public List<Venda> listar() {
        String sql = """
                SELECT id_venda, data_venda, valor_total, desconto,
                       usuario_id_usuario, cliente_id_cliente,
                       forma_pagamento_id_pagamento, status_id_status
                FROM venda
                ORDER BY data_venda DESC
                """;

        List<Venda> vendas = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Venda venda = new Venda();

                venda.setIdVenda(rs.getInt("id_venda"));

                if (rs.getTimestamp("data_venda") != null) {
                    venda.setDataVenda(rs.getTimestamp("data_venda").toLocalDateTime());
                }

                venda.setValorTotal(rs.getBigDecimal("valor_total"));
                venda.setDesconto(rs.getBigDecimal("desconto"));
                venda.setUsuarioIdUsuario(rs.getInt("usuario_id_usuario"));
                venda.setClienteIdCliente(rs.getInt("cliente_id_cliente"));
                venda.setFormaPagamentoIdPagamento(rs.getInt("forma_pagamento_id_pagamento"));
                venda.setStatusIdStatus(rs.getInt("status_id_status"));

                vendas.add(venda);
            }

        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao listar vendas: " + erro.getMessage());
        }

        return vendas;
    }
}