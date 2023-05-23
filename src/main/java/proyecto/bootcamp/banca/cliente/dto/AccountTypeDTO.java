package proyecto.bootcamp.banca.cliente.dto;

import lombok.Data;

@Data
public class AccountTypeDTO {
    private String id;
    private String name;
    private ConditionsDTO conditions;
}
