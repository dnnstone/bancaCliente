package proyecto.bootcamp.banca.cliente;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.services.ClientService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping()
    public List<Client> fetchAllClient(){
        return clientService.getAllClients();
    }
    @GetMapping("/{id}")
    public Client fechClient(@PathVariable("id") String id){
        return clientService.getClientbyId(id);
    }
}
