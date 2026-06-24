package sistema.controller;

import sistema.model.TipoMovimento;
import sistema.service.TipoMovimentoService;

import java.util.List;

public class TipoMovimentoController {

    private TipoMovimentoService tipoMovimentoService = new TipoMovimentoService();

    public void cadastrarTipoMovimento(String descricao) {
        tipoMovimentoService.cadastrarTipoMovimento(descricao);
    }

    public List<TipoMovimento> listarTiposMovimento() {
        return tipoMovimentoService.listarTiposMovimento();
    }
}