package sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MovimentoEstoque {

    private int idMovimento;
    private int quantidade;
    private LocalDateTime dataMovimento;
    private String observacao;

    private int produtoIdProduto;
    private int usuarioIdUsuario;
    private int tipoMovimentoIdMovimento;




}