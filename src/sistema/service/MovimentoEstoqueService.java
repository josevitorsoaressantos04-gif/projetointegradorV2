package sistema.service;

import sistema.DAO.MovimentoEstoqueDAO;
import sistema.model.MovimentoEstoque;
import sistema.model.Usuario;

import java.util.List;

public class MovimentoEstoqueService {

    private  MovimentoEstoqueDAO movimentoEstoqueDAO = new MovimentoEstoqueDAO();

    public void registrarMovimentacao(
            String idProdutoTexto,
            String quantidadeTexto,
            String tipoMovimento,
            String observacao,
            Usuario usuarioLogado
    ) {
        if (usuarioLogado == null) {
            throw new RuntimeException("Usuário logado não identificado.");
        }

        int idProduto = converterInteiro(idProdutoTexto, "produto");
        int quantidade = converterInteiro(quantidadeTexto, "quantidade");

        if (idProduto <= 0) {
            throw new RuntimeException("Informe um produto válido.");
        }

        if (quantidade <= 0) {
            throw new RuntimeException("A quantidade deve ser maior que zero.");
        }

        MovimentoEstoque movimento = new MovimentoEstoque();
        movimento.setProdutoIdProduto(idProduto);
        movimento.setQuantidade(quantidade);
        movimento.setObservacao(observacao);
        movimento.setUsuarioIdUsuario(usuarioLogado.getIdUsuario());

        if ("ENTRADA".equalsIgnoreCase(tipoMovimento)) {
            movimento.setTipoMovimentoIdMovimento(1);
            movimentoEstoqueDAO.registrarEntrada(movimento);

        } else if ("SAIDA".equalsIgnoreCase(tipoMovimento) || "SAÍDA".equalsIgnoreCase(tipoMovimento)) {
            movimento.setTipoMovimentoIdMovimento(2);
            movimentoEstoqueDAO.registrarSaida(movimento);

        } else {
            throw new RuntimeException("Tipo de movimentação inválido. Use ENTRADA ou SAIDA.");
        }
    }

    public List<MovimentoEstoque> listarMovimentacoes() {
        return movimentoEstoqueDAO.listar();
    }

    private int converterInteiro(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RuntimeException("Informe o campo " + campo + ".");
        }

        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException erro) {
            throw new RuntimeException("O campo " + campo + " deve ser um número válido.");
        }
    }
}