package proyecto.bootcamp.banca.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import proyecto.bootcamp.banca.cliente.model.Client;

public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
