package sistema.controller;

import sistema.model.Usuario;
import sistema.model.Venda;
import sistema.service.VendaService;

import java.util.List;

public class VendaController {

    private final VendaService vendaService = new VendaService();

    public int registrarVenda(
            String idCliente,
            String idProduto,
            String quantidade,
            String valorUnitario,
            String desconto,
            String idFormaPagamento,
            String idStatus,
            Usuario usuarioLogado
    ) {
        return vendaService.registrarVenda(
                idCliente,
                idProduto,
                quantidade,
                valorUnitario,
                desconto,
                idFormaPagamento,
                idStatus,
                usuarioLogado
        );
    }
    public List<Venda> listarVendas() {return vendaService.listarVendas();}
    public void excluirVenda(String idTexto){vendaService.excluirVenda(idTexto);}
}