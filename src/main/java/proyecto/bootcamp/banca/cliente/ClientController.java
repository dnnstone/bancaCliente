package proyecto.bootcamp.banca.cliente;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proyecto.bootcamp.banca.cliente.dto.Customer;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.dto.ReportClientProductsDTO;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.services.ClientService;
import io.reactivex.rxjava3.core.*;

import java.awt.*;
import java.util.List;

@RestController

@RequestMapping("api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @GetMapping("/{id}")
    public Maybe<Client> fechClient(@PathVariable("id") String id){
        Maybe<Client> clientbyId = clientService.getClientbyId(id);
        clientbyId.subscribe(new MaybeObserver<Client>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("Suscrito: "+d);
            }

            @Override
            public void onSuccess(@NonNull Client client) {
                System.out.println("Client:"+ client);
                ResponseEntity.ok().body(clientbyId);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("Error: "+e.getMessage());
                System.out.println("HttpStatus: 500");
            }

            @Override
            public void onComplete() {
                System.out.println("HttpStatus: 404");
                ResponseEntity.badRequest().body("incompatible");
            }
        });
        return clientbyId;
    }



    @GetMapping()
    public ResponseEntity<Flowable>  fetchAllClient(){
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    //this method is to get a report of clients
    //we're going to create a DTO to support our outcome and show as a json
    @GetMapping(value = "/reports/{nDoc}")
    public Maybe<ReportClientProductsDTO> getReportByDoc(@PathVariable("nDoc") String nDoc){
        return clientService.getInfoProductsClient(nDoc);
    }

    @PostMapping(value ="/create")
    public Single<Client> saveClient(@RequestBody InputClientDTO inputClientDTOS){
        return clientService.createClient(inputClientDTOS);
    }
    //those 3 methods are only to test.
    @GetMapping(value="/reactivex/customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public ResponseEntity<Flowable>  reactiveCustomers(RequestEntity request){
        Flowable<Customer> customerList= clientService.getAllCustomerStream();
        return ResponseEntity.ok().body(customerList);
    }
    @GetMapping(value="/reactivex/customersX", produces = MediaType.TEXT_EVENT_STREAM_VALUE )
    public Single<ResponseEntity> reactiveCustomersX(RequestEntity request){
        Flowable<Customer> customerList= clientService.getAllCustomerStream();
        return Single.just(ResponseEntity.ok().body(customerList)); // no funciona aun
    }
    @GetMapping (value="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Customer> loadCustomersStream(){
        return clientService.getAllCustomerStream();
    }
}
