package proyecto.bootcamp.banca.cliente.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.Movement;

import java.util.List;
@Data
public class ClientCreditDTO {

    private String id;
    private String nCredit;
    private Client client;
    private CardCreditDTO carClient;
    private List<Movement> movements;
    private Double limitCredit;
}
