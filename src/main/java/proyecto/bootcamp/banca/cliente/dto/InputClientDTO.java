package proyecto.bootcamp.banca.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputClientDTO {

    String nDoc;
    String tDoc;
    String names;
    String typeClient;
}
