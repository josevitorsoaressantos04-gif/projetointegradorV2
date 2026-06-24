package sistema.service;

import sistema.DAO.StatusDAO;
import sistema.model.Status;

import java.util.List;

public class StatusService {

    private StatusDAO statusDAO = new StatusDAO();

    public void cadastrarStatus(String idStatusTexto, String descricao) {

        int idStatus = converterInteiro(idStatusTexto, "status");

        if (descricao == null || descricao.trim().isEmpty()) {
            throw new RuntimeException("Informe a descrição do status.");
        }

        if (descricao.trim().length() > 100) {
            throw new RuntimeException("A descrição do status não pode ultrapassar 100 caracteres.");
        }

        Status status = new Status();
        status.setIdStatus(idStatus);
        status.setDescricao(descricao.trim());

        statusDAO.inserir(status);
    }

    public List<Status> listarStatus() {
        return statusDAO.listar();
    }

    private int converterInteiro(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new RuntimeException("Informe o ID do " + campo + ".");
        }

        try {
            int id = Integer.parseInt(texto.trim());

            if (id <= 0) {
                throw new RuntimeException("Informe um ID válido para " + campo + ".");
            }

            return id;

        } catch (NumberFormatException erro) {
            throw new RuntimeException("O ID do " + campo + " deve ser um número válido.");
        }
    }
}