package proyecto.bootcamp.banca.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import proyecto.bootcamp.banca.cliente.model.ClientType;

public interface ClientTypeRepository
        extends MongoRepository<ClientType,String> {

}
