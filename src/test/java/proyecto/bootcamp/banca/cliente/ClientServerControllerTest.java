package proyecto.bootcamp.banca.cliente;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.model.AccountConditions;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.model.CreditConditions;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;
import proyecto.bootcamp.banca.cliente.services.ClientService;
import reactor.adapter.rxjava.RxJava3Adapter;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ClientServerControllerTest {
    @Autowired
    private WebTestClient webTestClient; //considerar tener webflux como dependencia para q funcione correctamente
    @MockBean
    private ClientService clientService;
    private static Client clientMock;
    private final static String ID="IDdeMONGO";
    private final static String NDOC="20401854";
    private final static ClientType personalType= new ClientType("6466d4b6d90ed775c7863f8d", "Personal",new AccountConditions(2,1,1,1,true),new CreditConditions(1));
    private final static ClientType empresariaType= new ClientType("6466d4b7d90ed775c7863f8e", "Empresarial",new AccountConditions(-1,0,-1,0,false),new CreditConditions(-1));

    private static InputClientDTO inputClientDTO;
    @BeforeAll
    static void setUp(){

                clientMock= new Client(ID, NDOC,"DNI", "Victor Alcocer",personalType);
                inputClientDTO= new InputClientDTO("2010321232","Ruc","Corporación Sarita",empresariaType.getId());

    }

    @Test
    @DisplayName("Get -> /api/v1/client/{id}")
    void fechClient(){
    when(clientService.getClientbyId(ID)).thenReturn(Maybe.just(clientMock));
    WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("api/v1/client/"+ID)
            .accept(MediaType.APPLICATION_JSON)
            .exchange();
    responseSpec.expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON);
    responseSpec.expectBody()
            .jsonPath("$.names").isEqualTo(clientMock.getNames());

    }
    @Test
    @DisplayName("Get->/api/v1/client/doc/{nDoc}")
    void byNdoc(){
        when(clientService.getClienteByDoc(NDOC)).thenReturn(Maybe.just(clientMock));
        WebTestClient.ResponseSpec responseSpec= webTestClient.get().uri("api/v1/client/doc/"+NDOC)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
        responseSpec.expectBody()
                .jsonPath("$.names").isEqualTo(clientMock.getNames());
    }

    @Test
    @DisplayName("Post-> /create")
    void create(){
        when(clientService.createClient(inputClientDTO)).thenReturn(Single.just(new Client(inputClientDTO.getNDoc(),inputClientDTO.getTDoc(),inputClientDTO.getNames(),empresariaType)));

        WebTestClient.ResponseSpec responseSpec= webTestClient.post().uri("api/v1/client/create")
                .body(Mono.just(inputClientDTO),InputClientDTO.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange();
        responseSpec.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON);
        responseSpec.expectBody()
                .jsonPath("$.names").isEqualTo(inputClientDTO.getNames());
    }
}
