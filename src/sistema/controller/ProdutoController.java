package sistema.controller;

import sistema.model.Produto;
import sistema.service.ProdutoService;

import java.util.List;

public class ProdutoController {

    private ProdutoService produtoService = new ProdutoService();

    public void cadastrarProduto(
            String nome,
            String descricao,
            String valorCusto,
            String valorVenda,
            Integer estoque
    ) {
        produtoService.cadastrarProduto(nome, descricao, valorCusto, valorVenda, estoque);
    }

    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    public void excluirProduto(String idTexto) {
        produtoService.excluirProduto(idTexto);
    }
}