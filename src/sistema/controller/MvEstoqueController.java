package sistema.controller;

import sistema.model.MovimentoEstoque;
import sistema.model.Usuario;
import sistema.service.MovimentoEstoqueService;

import java.util.List;

public class MvEstoqueController {

    private MovimentoEstoqueService movimentoEstoqueService = new MovimentoEstoqueService();

    public void registrarMovimentacao(
            String idProduto,
            String quantidade,
            String tipoMovimento,
            String observacao,
            Usuario usuarioLogado
    ) {
        movimentoEstoqueService.registrarMovimentacao(
                idProduto,
                quantidade,
                tipoMovimento,
                observacao,
                usuarioLogado
        );
    }

    public List<MovimentoEstoque> listarMovimentacoes() {
        return movimentoEstoqueService.listarMovimentacoes();
    }
}