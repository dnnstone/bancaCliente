package proyecto.bootcamp.banca.cliente.model;

import lombok.Data;

@Data
public class CreditConditions {
    private Integer maxCredits;

    public CreditConditions(Integer maxCredits) {
        this.maxCredits = maxCredits;
    }
}
