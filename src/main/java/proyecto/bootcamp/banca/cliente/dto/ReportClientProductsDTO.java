package proyecto.bootcamp.banca.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportClientProductsDTO {

    String clientName;
    List<AccountInfoDTO> accountInfoDTOList;
    List<CreditInfoDTO> creditsInfoDTOList;

}
