package sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVenda {

    private int idItem;
    private int quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    private int vendaIdVenda;
    private int produtoIdProduto;
    private int vendaIdVenda1;

}