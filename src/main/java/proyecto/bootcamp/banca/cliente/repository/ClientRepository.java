package proyecto.bootcamp.banca.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import proyecto.bootcamp.banca.cliente.model.Client;

public interface ClientRepository extends MongoRepository <Client, String>{
}
