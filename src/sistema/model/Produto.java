package sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private int idProduto;
    private String nome;
    private String descricao;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private int estoque;
}