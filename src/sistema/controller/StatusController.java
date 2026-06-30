package sistema.controller;

import sistema.model.Status;
import sistema.service.StatusService;

import java.util.List;

public class StatusController {

    private StatusService statusService = new StatusService();

    public void cadastrarStatus(String descricao) {
        statusService.cadastrarStatus(descricao);
    }

    public List<Status> listarStatus() {
        return statusService.listarStatus();
    }
}