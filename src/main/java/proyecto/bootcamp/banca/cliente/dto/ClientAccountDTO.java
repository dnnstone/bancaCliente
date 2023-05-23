package proyecto.bootcamp.banca.cliente.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.Movement;
import lombok.Data;
import java.util.List;

@Data
public class ClientAccountDTO {
    private String id;
    private String nAccount;
    private AccountTypeDTO accountType;
    private Client client;
    private List<Movement> movements;
    private Double saldo;
}
