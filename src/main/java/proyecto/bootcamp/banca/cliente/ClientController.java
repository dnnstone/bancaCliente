package proyecto.bootcamp.banca.cliente;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proyecto.bootcamp.banca.cliente.dto.Customer;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.services.ClientService;
import io.reactivex.rxjava3.core.*;

import java.awt.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;


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
