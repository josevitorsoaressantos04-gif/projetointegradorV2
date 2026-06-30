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
            Integer estoqueTexto
    ) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do produto.");
        }

        BigDecimal valorCusto;
        BigDecimal valorVenda;
        Integer estoque = 0;

        try {
            valorCusto = new BigDecimal(valorCustoTexto);
            valorVenda = new BigDecimal(valorVendaTexto);
        } catch (NumberFormatException erro) {
            throw new RuntimeException("Informe valores válidos para custo, venda e estoque.");
        }

        if (valorCusto.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("O valor de custo não pode ser negativo.");
        }

        if (valorVenda.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor de venda deve ser maior que zero.");
        }

        if (estoque == null || estoque <0) {
            throw new RuntimeException("O estoque não pode ser negativo ou nulo.");
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
    public void editarProduto(
            String idTexto,
            String nome,
            String descricao,
            String valorCustoTexto,
            String valorVendaTexto,
            String estoqueTexto
    ) {
        int idProduto = converterInteiro(idTexto, "produto");

        if (idProduto <= 0) {
            throw new RuntimeException("Informe um ID de produto válido.");
        }

        validarNome(nome);

        BigDecimal valorCusto = converterDecimal(valorCustoTexto, "valor de custo");
        BigDecimal valorVenda = converterDecimal(valorVendaTexto, "valor de venda");
        int estoque = converterInteiro(estoqueTexto, "estoque");

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
        produto.setIdProduto(idProduto);
        produto.setNome(nome.trim());
        produto.setDescricao(descricao);
        produto.setValorCusto(valorCusto);
        produto.setValorVenda(valorVenda);
        produto.setEstoque(estoque);

        produtoDAO.atualizar(produto);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do produto.");
        }

        if (nome.trim().length() > 200) {
            throw new RuntimeException("O nome do produto ultrapassou o limite de 200 caracteres.");
        }
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

    private BigDecimal converterDecimal(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RuntimeException("Informe o campo " + campo + ".");
        }

        try {
            return new BigDecimal(texto.trim().replace(",", "."));
        } catch (NumberFormatException erro) {
            throw new RuntimeException("O campo " + campo + " deve ser um valor válido.");
        }
    }
}
