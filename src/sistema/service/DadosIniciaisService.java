package sistema.service;

import sistema.DAO.DadosIniciaisDAO;

public class DadosIniciaisService {

    private DadosIniciaisDAO dadosIniciaisDAO = new DadosIniciaisDAO();

    public void inserirDadosIniciais() {
        dadosIniciaisDAO.inserirDadosIniciais();
    }
}