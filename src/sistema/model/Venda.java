package sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venda {

    private int idVenda;
    private LocalDateTime dataVenda;
    private BigDecimal valorTotal;
    private BigDecimal desconto;

    private int usuarioIdUsuario;
    private int clienteIdCliente;
    private int formaPagamentoIdPagamento;
    private int statusIdStatus;
}