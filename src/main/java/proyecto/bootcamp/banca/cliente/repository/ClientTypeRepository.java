package proyecto.bootcamp.banca.cliente.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import proyecto.bootcamp.banca.cliente.model.ClientType;

public interface ClientTypeRepository
        extends ReactiveMongoRepository<ClientType,String> {

}
