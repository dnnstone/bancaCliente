package proyecto.bootcamp.banca.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConditionsDTO {
    private Integer maintainFee;
    private Integer maxMovement;
    private Integer diaMovement;
    private String dataHeadlines;
    private String dataSigners;
    private Double minAmount;
    private Boolean withCreditCar;
    private Boolean withCurrentAccount;
    private Double chargeOfTransaction;

}
