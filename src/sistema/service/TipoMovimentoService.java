package sistema.service;

import sistema.DAO.TipoMovimentoDAO;
import sistema.model.TipoMovimento;

import java.util.List;

public class TipoMovimentoService {

    private TipoMovimentoDAO tipoMovimentoDAO = new TipoMovimentoDAO();

    public void cadastrarTipoMovimento(String descricao) {

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new RuntimeException("Informe a descrição do tipo de movimento.");
        }

        if (descricao.trim().length() > 100) {
            throw new RuntimeException("A descrição do tipo de movimento não pode ultrapassar 100 caracteres.");
        }

        TipoMovimento tipoMovimento = new TipoMovimento();
        tipoMovimento.setDescricao(descricao.trim());

        tipoMovimentoDAO.inserir(tipoMovimento);
    }

    public List<TipoMovimento> listarTiposMovimento() {
        return tipoMovimentoDAO.listar();
    }
}