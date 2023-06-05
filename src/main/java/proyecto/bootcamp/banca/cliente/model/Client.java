package proyecto.bootcamp.banca.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "client")
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    private String id;
    @Indexed(unique = true)
    private String nDoc;
    private String tDoc;
    private String names;
    private ClientType typeClient;

    public Client(String nDoc, String tDoc, String names, ClientType typeClient) {
        this.nDoc = nDoc;
        this.tDoc = tDoc;
        this.names = names;
        this.typeClient = typeClient;
    }
}
