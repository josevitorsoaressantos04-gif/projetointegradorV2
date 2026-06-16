package sistema.service;

import sistema.DAO.UsuarioDAO;

public class ExcluirUsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void excluirUsuario(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome do usuário para exclusão.");
        }

        usuarioDAO.excluir(nome);
    }
}