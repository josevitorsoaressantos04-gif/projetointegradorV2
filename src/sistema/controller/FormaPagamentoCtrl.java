package sistema.controller;

import sistema.model.FormaPagamento;
import sistema.service.FormaPagamentoService;

import java.util.List;

public class FormaPagamentoCtrl {

    private FormaPagamentoService formaPagamentoService = new FormaPagamentoService();

    public void cadastrarFormaPagamento(String descricao) {
        formaPagamentoService.cadastrarFormaPagamento(descricao);
    }

    public List<FormaPagamento> listarFormasPagamento() {
        return formaPagamentoService.listarFormasPagamento();
    }
}