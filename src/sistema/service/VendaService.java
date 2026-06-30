package sistema.service;

import sistema.DAO.VendaDAO;
import sistema.model.ItemVenda;
import sistema.model.Usuario;
import sistema.model.Venda;

import java.math.BigDecimal;
import java.util.List;

public class VendaService {

    private VendaDAO vendaDAO = new VendaDAO();

    public int registrarVenda(
            String idClienteTexto,
            String idProdutoTexto,
            String quantidadeTexto,
            String valorUnitarioTexto,
            String descontoTexto,
            String idFormaPagamentoTexto,
            String idStatusTexto,
            Usuario usuarioLogado
    ) {
        if (usuarioLogado == null) {
            throw new RuntimeException("Usuário logado não identificado.");
        }

        int idCliente = converterInteiro(idClienteTexto, "cliente");
        int idProduto = converterInteiro(idProdutoTexto, "produto");
        int quantidade = converterInteiro(quantidadeTexto, "quantidade");
        int idFormaPagamento = converterInteiro(idFormaPagamentoTexto, "forma de pagamento");
        int idStatus = converterInteiro(idStatusTexto, "status");

        BigDecimal valorUnitario = converterDecimal(valorUnitarioTexto, "valor unitário");
        BigDecimal desconto = converterDecimalOpcional(descontoTexto);

        validarDadosVenda(
                idCliente,
                idProduto,
                quantidade,
                idFormaPagamento,
                idStatus,
                valorUnitario,
                desconto
        );

        BigDecimal valorTotalItem = valorUnitario.multiply(BigDecimal.valueOf(quantidade));
        BigDecimal valorTotalVenda = valorTotalItem.subtract(desconto);

        if (valorTotalVenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor total da venda deve ser maior que zero.");
        }

        Venda venda = new Venda();
        venda.setValorTotal(valorTotalVenda);
        venda.setDesconto(desconto);
        venda.setUsuarioIdUsuario(usuarioLogado.getIdUsuario());
        venda.setClienteIdCliente(idCliente);
        venda.setFormaPagamentoIdPagamento(idFormaPagamento);
        venda.setStatusIdStatus(idStatus);


        /*
         * Status 1 = Finalizada
         * Ajuste esse ID se no seu banco o status finalizado tiver outro código.
         */
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setProdutoIdProduto(idProduto);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setValorUnitario(valorUnitario);
        itemVenda.setValorTotal(valorTotalItem);

        try {
            return vendaDAO.inserirVendaComItem(venda, itemVenda);

        } catch (RuntimeException erro) {
            throw new RuntimeException("Erro ao registrar venda: " + erro.getMessage());
        }
    }

    public List<Venda> listarVendas() {
        try {
            return vendaDAO.listar();

        } catch (RuntimeException erro) {
            throw new RuntimeException("Erro ao listar vendas: " + erro.getMessage());
        }
    }

    private void validarDadosVenda(
            int idCliente,
            int idProduto,
            int quantidade,
            int idFormaPagamento,
            int idStatus,
            BigDecimal valorUnitario,
            BigDecimal desconto
    ) {
        if (idCliente <= 0) {
            throw new RuntimeException("Informe um cliente válido.");
        }

        if (idProduto <= 0) {
            throw new RuntimeException("Informe um produto válido.");
        }

        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero.");
        }

        if (idFormaPagamento <= 0) {
            throw new RuntimeException("Informe uma forma de pagamento válida.");
        }

        if (idStatus <0){
            throw new RuntimeException("Informe um status de venda válido.");
        }

        if (valorUnitario == null || valorUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor unitário deve ser maior que zero.");
        }

        if (desconto == null) {
            throw new RuntimeException("O desconto não pode ser nulo.");
        }

        if (desconto.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("O desconto não pode ser negativo.");
        }

        BigDecimal valorTotalItem = valorUnitario.multiply(BigDecimal.valueOf(quantidade));

        if (desconto.compareTo(valorTotalItem) > 0) {
            throw new RuntimeException("O desconto não pode ser maior que o valor total dos itens.");
        }
    }

    private int converterInteiro(String valorTexto, String nomeCampo) {
        if (valorTexto == null || valorTexto.trim().isEmpty()) {
            throw new RuntimeException("Informe o campo " + nomeCampo + ".");
        }

        try {
            return Integer.parseInt(valorTexto.trim());

        } catch (NumberFormatException erro) {
            throw new RuntimeException("O campo " + nomeCampo + " deve ser um número válido.");
        }
    }

    private BigDecimal converterDecimal(String valorTexto, String nomeCampo) {
        if (valorTexto == null || valorTexto.trim().isEmpty()) {
            throw new RuntimeException("Informe o campo " + nomeCampo + ".");
        }

        try {
            String valorTratado = valorTexto.trim().replace(",", ".");
            return new BigDecimal(valorTratado);

        } catch (NumberFormatException erro) {
            throw new RuntimeException("O campo " + nomeCampo + " deve ser um valor monetário válido.");
        }
    }

    private BigDecimal converterDecimalOpcional(String valorTexto) {
        if (valorTexto == null || valorTexto.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            String valorTratado = valorTexto.trim().replace(",", ".");
            return new BigDecimal(valorTratado);

        } catch (NumberFormatException erro) {
            throw new RuntimeException("O campo desconto deve ser um valor monetário válido.");
        }
    }
}