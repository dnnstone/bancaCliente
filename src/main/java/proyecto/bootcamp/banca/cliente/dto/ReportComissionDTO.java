package proyecto.bootcamp.banca.cliente.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportComissionDTO {
    private String producto;
    private Long countTransactions;
    private Double amountTotalComission;
    List<TransactionDTO> transactionList;

}
