package proyecto.bootcamp.banca.cliente.services;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import proyecto.bootcamp.banca.cliente.dto.*;
import proyecto.bootcamp.banca.cliente.model.Client;

public interface ClientService{

    public Flowable<Client> getAllClients();
    public Maybe<Client> getClientbyId(String id);
    public Maybe<Client> getClienteByDoc(String nDoc);
    public Maybe<ReportClientProductsDTO> getInfoProductsClient(String nDoc);
    public Flowable<Customer> getAllCustomerStream();
    public Single createClient (InputClientDTO inputClientDTOSingle);



}
