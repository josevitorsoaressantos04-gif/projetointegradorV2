package sistema.service;

import sistema.DAO.ProdutoDAO;
import sistema.model.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public void cadastrarProduto(
            String nome,
            String descricao,
            String valorCustoTexto,
            String valorVendaTexto,
            String estoqueTexto
    ) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do produto.");
        }

        BigDecimal valorCusto;
        BigDecimal valorVenda;
        int estoque;

        try {
            valorCusto = new BigDecimal(valorCustoTexto);
            valorVenda = new BigDecimal(valorVendaTexto);
            estoque = Integer.parseInt(estoqueTexto);
        } catch (NumberFormatException erro) {
            throw new RuntimeException("Informe valores válidos para custo, venda e estoque.");
        }

        if (valorCusto.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("O valor de custo não pode ser negativo.");
        }

        if (valorVenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor de venda deve ser maior que zero.");
        }

        if (estoque < 0) {
            throw new RuntimeException("O estoque não pode ser negativo.");
        }

        Produto produto = new Produto();
        produto.setNome(nome.trim());
        produto.setDescricao(descricao);
        produto.setValorCusto(valorCusto);
        produto.setValorVenda(valorVenda);
        produto.setEstoque(estoque);

        produtoDAO.inserir(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoDAO.listar();
    }

    public void excluirProduto(String idTexto) {

        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new RuntimeException("Informe o ID do produto para exclusão.");
        }

        int idProduto;

        try {
            idProduto = Integer.parseInt(idTexto);
        } catch (NumberFormatException erro) {
            throw new RuntimeException("O ID do produto deve ser um número válido.");
        }

        if (idProduto <= 0) {
            throw new RuntimeException("Informe um ID válido.");
        }

        produtoDAO.excluir(idProduto);
    }
}