package sistema.controller;

import sistema.model.Cliente;
import sistema.service.ClienteService;

import java.util.List;

public class ClienteController {

    private ClienteService clienteService = new ClienteService();

    public void cadastrarCliente(String nome, String cpfCnpj, String telefone, String email) {
        clienteService.cadastrarCliente(nome, cpfCnpj, telefone, email);
    }

    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public void excluirCliente(String idTexto) {
        clienteService.excluirCliente(idTexto);
    }
    public void editarCliente(
            String id,
            String nome,
            String cpfCnpj,
            String telefone,
            String email
    ) {
        clienteService.editarCliente(id, nome, cpfCnpj, telefone, email);
    }

}