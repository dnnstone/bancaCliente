package proyecto.bootcamp.banca.cliente.services;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import proyecto.bootcamp.banca.cliente.dao.CustomerComponent;
import proyecto.bootcamp.banca.cliente.dto.*;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.repository.ClientRepository;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import reactor.adapter.rxjava.RxJava3Adapter;

@AllArgsConstructor
@Service
public class ClientService {


    private final ClientRepository clientRepository;

    private final ClientTypeRepository clientTypeRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private CustomerComponent customerComponent;

    @Autowired
    private final ReactiveMongoTemplate mongoTemplate;
    @Autowired
    private Environment env;
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
    public Maybe<Client> getClienteByDoc(String nDoc){
        Query query =new Query();
        query.addCriteria(Criteria.where("nDoc").is(nDoc));
        return mongoTemplate
                .findOne(query, Client.class)
                .as(RxJava3Adapter::monoToMaybe);

    }
    public Maybe<ReportClientProductsDTO> getInfoProductsClient(String nDoc){

        return this.getClienteByDoc(nDoc).map(cli->{
            ReportClientProductsDTO reportClientProductsDTO= new ReportClientProductsDTO();
            reportClientProductsDTO.setClientName(cli.getNames());
            RestTemplate restTemplate= new RestTemplate();

            String url=env.getProperty("apis.account")+"client/ndoc/"+cli.getNDoc();
            logger.info("url accounts: "+url);
            ClientAccountDTO[] clientAccountDTOS= restTemplate
                    .getForObject(url, ClientAccountDTO[].class);
            List<AccountInfoDTO> accountInfoDTOList= Arrays.stream(clientAccountDTOS)
                    .map(data->new AccountInfoDTO(data.getNAccount(),data.getSaldo()))
                    .collect(Collectors.toList());
            reportClientProductsDTO.setAccountInfoDTOList(accountInfoDTOList);

            url=env.getProperty("apis.credit")+"client/ndoc/"+cli.getNDoc();
            logger.info("url credit: "+url);
            ClientCreditDTO[] clientCreditDTOS= restTemplate
                    .getForObject(url,ClientCreditDTO[].class);
            List<CreditInfoDTO> creditInfoDTOS= Arrays.stream(clientCreditDTOS)
                    .map(data-> new CreditInfoDTO(data.getNCredit(),data.getLimitCredit()))
                    .collect(Collectors.toList());
            reportClientProductsDTO.setCreditsInfoDTOList(creditInfoDTOS);

            return reportClientProductsDTO;
        });

    }
    public Flowable<Customer> getAllCustomerStream(){
        Long start= System.currentTimeMillis();
        Flowable<Customer> allCustomerStream = customerComponent.getAllCustomerStream();
        Long end= System.currentTimeMillis();
        logger.info("it take: "+(end-start)+ " micro");
        return allCustomerStream;

    }


}
