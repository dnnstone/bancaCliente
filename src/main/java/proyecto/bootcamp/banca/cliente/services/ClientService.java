package proyecto.bootcamp.banca.cliente.services;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import proyecto.bootcamp.banca.cliente.dao.CustomerComponent;
import proyecto.bootcamp.banca.cliente.dto.Customer;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.repository.ClientRepository;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;


import java.util.List;
import org.slf4j.Logger;
import reactor.adapter.rxjava.RxJava3Adapter;

@AllArgsConstructor
@Service
public class ClientService {


    private final ClientRepository clientRepository;

    private final ClientTypeRepository clientTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private CustomerComponent customerComponent;


    public Flowable<Client> getAllClients(){

        return clientRepository.findAll().as(RxJava3Adapter::fluxToFlowable);

    }
//    Metodo que devolverá toda la información actualizada de un cliente
    public Maybe<Client> getClientbyId(String id){

        return clientRepository.findById(id).as(RxJava3Adapter::monoToMaybe);

//        Client client =clientRepository.findById(id).isPresent()?clientRepository.findById(id).get():null;
//        ClientType clientType= clientTypeRepository.findById(client.getTypeClient().getId()).get();
//        logger.info("Dnns: request a este micro");
//        logger.info("Dnns: se solicito info de cliente: "+client.getNames());
//        client.setTypeClient(clientType);
//        return client;
    }
//    public List<Customer> getAllCustomer(){
//        Long start= System.currentTimeMillis();
//        List<Customer> allCustomer = customerComponent.getAllCustomer();
//        Long end= System.currentTimeMillis();
//        logger.info("it take: "+(end-start)+ " micro");
//        return allCustomer;
//
//    }
    public Flowable<Customer> getAllCustomerStream(){
        Long start= System.currentTimeMillis();
        Flowable<Customer> allCustomerStream = customerComponent.getAllCustomerStream();
        Long end= System.currentTimeMillis();
        logger.info("it take: "+(end-start)+ " micro");
        return allCustomerStream;

    }

}
