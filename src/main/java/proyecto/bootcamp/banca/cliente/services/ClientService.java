package proyecto.bootcamp.banca.cliente.services;

import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.repository.ClientRepository;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;

import java.util.List;
import org.slf4j.Logger;

@AllArgsConstructor
@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientTypeRepository clientTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);


    public List<Client> getAllClients()
    {
        return clientRepository.findAll();
    }
//    Metodo que devolverá toda la información actualizada de un cliente
    public Client getClientbyId(String id){
        Client client =clientRepository.findById(id).isPresent()?clientRepository.findById(id).get():null;
        ClientType clientType= clientTypeRepository.findById(client.getTypeClient().getId()).get();
        logger.info("Dnns: request a este micro");
        logger.info("Dnns: se solicito info de cliente: "+client.getNames());
        client.setTypeClient(clientType);
        return client;
    }
}
