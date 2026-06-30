package sistema.service;

import sistema.DAO.ClienteDAO;
import sistema.model.Cliente;

import java.util.List;

public class ClienteService {

    private ClienteDAO clienteDAO = new ClienteDAO();

    public void cadastrarCliente(String nome, String cpfCnpj, String telefone, String email) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome da pessoa.");
        }

        if (nome.length() > 200) {
            throw new RuntimeException("O nome ultrapassou o limite máximo de 200 caracteres.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(nome.trim());
        cliente.setCpfCnpj(cpfCnpj);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);

        clienteDAO.cadastrarCliente(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public void excluirCliente(String idTexto) {

        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new RuntimeException("Informe o ID da pessoa para exclusão.");
        }

        int idCliente;

        try {
            idCliente = Integer.parseInt(idTexto);
        } catch (NumberFormatException erro) {
            throw new RuntimeException("O ID da pessoa deve ser um número válido.");
        }

        if (idCliente <= 0) {
            throw new RuntimeException("Informe um ID válido.");
        }

        if (validarVinculoCliente(idCliente)) {
            throw new RuntimeException("Não é possível excluir este cliente, pois ele possui venda vinculada.");
        }

        clienteDAO.excluirCliente(idCliente);
    }

    public void editarCliente(
            String idTexto,
            String nome,
            String cpfCnpj,
            String telefone,
            String email
    ) {
        if (idTexto == null || idTexto.trim().isEmpty()) {
            throw new RuntimeException("Informe o ID da pessoa para edição.");
        }

        int idCliente;

        try {
            idCliente = Integer.parseInt(idTexto);
        } catch (NumberFormatException erro) {
            throw new RuntimeException("O ID da pessoa deve ser um número válido.");
        }

        if (idCliente <= 0) {
            throw new RuntimeException("Informe um ID válido.");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Informe o nome da pessoa.");
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setNome(nome.trim());
        cliente.setCpfCnpj(cpfCnpj);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);

        clienteDAO.atualizarCliente(cliente);
    }

    public boolean validarVinculoCliente(int idCliente) {
        return clienteDAO.vrClienteVenda(idCliente);
    }
}
