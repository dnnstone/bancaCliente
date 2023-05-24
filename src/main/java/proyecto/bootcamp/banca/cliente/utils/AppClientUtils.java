package proyecto.bootcamp.banca.cliente.utils;


import org.springframework.beans.BeanUtils;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.model.Client;

public class AppClientUtils {
//    public ClientDto entityToDto
    public static Client dtoToEntity(InputClientDTO inputClientDTO){
        Client client= new Client();
        BeanUtils.copyProperties(inputClientDTO,client,"typeClient");
        return client;


    }
}
