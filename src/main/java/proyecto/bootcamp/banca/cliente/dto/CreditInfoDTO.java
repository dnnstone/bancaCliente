package proyecto.bootcamp.banca.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditInfoDTO {
    String numberCredit;
    Double amount;
}
