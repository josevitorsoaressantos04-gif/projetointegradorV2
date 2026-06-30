package sistema.controller;

import sistema.service.DadosIniciaisService;

public class DadosIniciaisController {

    private DadosIniciaisService dadosIniciaisService = new DadosIniciaisService();

    public void inserirDadosIniciais() {
        dadosIniciaisService.inserirDadosIniciais();
    }
}