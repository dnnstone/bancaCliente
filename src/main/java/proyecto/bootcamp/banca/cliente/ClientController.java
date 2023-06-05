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
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.util.List;

@RestController

@RequestMapping("api/v1/client")
public class ClientController {
    @Autowired
    private ClientService clientService;


    @GetMapping("/{id}")
    public Single<ResponseEntity<Client>> fechClient(@PathVariable("id") String id){
        return clientService.getClientbyId(id)
                .map(s->ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON).body(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/doc/{ndoc}")
    public Single<ResponseEntity<Client>>clientByDoc(@PathVariable("ndoc") String ndoc){
        return  clientService.getClienteByDoc(ndoc)
                .map(s->ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON).body(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Single<ResponseEntity<Flowable<Client>>>  fetchAllClient(){
            return Single.just(ResponseEntity.ok().body(clientService.getAllClients()));

    }

    //this method is to get a report of clients
    //we're going to create a DTO to support our outcome and show as a json
    @GetMapping(value = "/reports/{nDoc}")
    public Single<ResponseEntity<ReportClientProductsDTO>> getReportByDoc(@PathVariable("nDoc") String nDoc) {

        return clientService.getInfoProductsClient(nDoc)
                .map(s -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON).body(s))
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @PostMapping(value ="/create")
    public Single<Client> saveClient(@RequestBody InputClientDTO inputClientDTOS){
        return clientService.createClient(inputClientDTOS).map(s->ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON).body(s))
                ;
    }
    //those 3 methods are only to test.
}
