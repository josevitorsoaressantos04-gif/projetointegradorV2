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
            String estoque
    ) {
        produtoService.cadastrarProduto(nome, descricao, valorCusto, valorVenda, estoque);
    }

    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }


    public void editarProduto(
            String id,
            String nome,
            String descricao,
            String valorCusto,
            String valorVenda,
            String estoque
    ) {
        produtoService.editarProduto(id, nome, descricao, valorCusto, valorVenda, estoque);
    }

    public void excluirProduto(String id) {
        produtoService.excluirProduto(id);
    }

}