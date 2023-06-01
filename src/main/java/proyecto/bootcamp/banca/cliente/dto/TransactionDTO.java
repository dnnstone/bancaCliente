package proyecto.bootcamp.banca.cliente.dto;

import lombok.Data;

import java.util.Date;
@Data
public class TransactionDTO {
    private Date date;
    private String operation;
    private Double amount;
}
