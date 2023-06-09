package proyecto.bootcamp.banca.cliente;

import io.reactivex.rxjava3.core.Single;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;
import proyecto.bootcamp.banca.cliente.services.ClientService;
import proyecto.bootcamp.banca.cliente.utils.AppClientUtils;
import proyecto.bootcamp.banca.cliente.ClientController;
import reactor.adapter.rxjava.RxJava3Adapter;

import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;
import java.lang.String;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteApplicationTests {

//	@Autowired
//	private Environment env;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	@Autowired
	private MockMvc mockMvc;

	private WebTestClient webTestClient;


//	void addClient() {
//		ClientType personalType=clientTypeRepository.findById("6466d4b6d90ed775c7863f8d").as(RxJava3Adapter::monoToSingle).blockingGet();
//		Single<Client> client= Single.just(new Client("20401854","DNI", "Victor Alcocer",personalType));
//
//		InputClientDTO inputClientDTO= new InputClientDTO("20401854","DNI", "Victor Alcocer","6466d4b6d90ed775c7863f8d");
//		when(clientService.createClient(inputClientDTO)).thenReturn(client);
//
//		webTestClient.post().uri("/api/v1/client/create")
//				.body(inputClientDTO,InputClientDTO.class)
//				.exchange()
//				.expectStatus().isOk();
//	}


//	void testCreationClient(){
//
//		ClientType clientTypePersonal= clientTypeRepository.findById("6466d4b6d90ed775c7863f8d").block();
//		ClientType clientTypeEmpresarial= clientTypeRepository.findById("6466d4b7d90ed775c7863f8e").block();
//		Client client= new Client(AppClientUtils.createNumber(),"DNI","Test1",clientTypePersonal);
//
//		client= (Client)clientService.createClient(new InputClientDTO(client.getNDoc()
//				,client.getTDoc()
//				,"name: "+client.getNDoc()
//				,client.getTypeClient().getId())).blockingGet();
//	}
////	@Test
////	@RepeatedTest(value = 2)
//	void testCreationClientIndex(){
//		ClientType clientTypePersonal= clientTypeRepository.findById("6466d4b6d90ed775c7863f8d").block();
//		ClientType clientTypeEmpresarial= clientTypeRepository.findById("6466d4b7d90ed775c7863f8e").block();
//		Client client= new Client("06956020972756687298","DNI","Test1",clientTypePersonal);
//
//		client= (Client)clientService.createClient(new InputClientDTO(client.getNDoc()
//				,client.getTDoc()
//				,"name: "+client.getNDoc()
//				,client.getTypeClient().getId())).blockingGet();
//	}

	@ParameterizedTest
	@ValueSource(strings= {"647a0a8ea1d86473c3e4930b","647a0a8ea1d86473c3e4930a"})
	void testEndpointById(String id) throws Exception{
		System.out.println("id:" +id);
		String response= mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/nuevo/{id}",id)
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse()
				.getContentAsString()
				;
		System.out.println("res:" +response);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/{id}",id)
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getOutputStream()
				.println();

	}


}
