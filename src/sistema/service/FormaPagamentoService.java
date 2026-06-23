package sistema.service;

import sistema.DAO.FormaPagamentoDAO;
import sistema.model.FormaPagamento;

import java.util.List;

public class FormaPagamentoService {

    private FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();

    public void cadastrarFormaPagamento(String descricao) {

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new RuntimeException("Informe a descrição da forma de pagamento.");
        }

        if (descricao.trim().length() > 100) {
            throw new RuntimeException("A descrição da forma de pagamento não pode ultrapassar 100 caracteres.");
        }

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setDescricao(descricao.trim());

        formaPagamentoDAO.inserir(formaPagamento);
    }

    public List<FormaPagamento> listarFormasPagamento() {
        return formaPagamentoDAO.listar();
    }
}