package sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecuperacaoSenha {

    private int idUsuario;
    private String nome;
    private String login;
    private String senha;
    private String recuperacaoSenha;
    private LocalDateTime dataCadastro;
}