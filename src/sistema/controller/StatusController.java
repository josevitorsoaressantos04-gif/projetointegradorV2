package sistema.controller;

import sistema.model.Status;
import sistema.service.StatusService;

import java.util.List;

public class StatusController {

    private StatusService statusService = new StatusService();

    public void cadastrarStatus(String idStatus, String descricao) {
        statusService.cadastrarStatus(idStatus, descricao);
    }

    public List<Status> listarStatus() {
        return statusService.listarStatus();
    }
}