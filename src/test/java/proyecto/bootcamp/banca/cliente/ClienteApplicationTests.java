package proyecto.bootcamp.banca.cliente;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import proyecto.bootcamp.banca.cliente.dto.InputClientDTO;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;
import proyecto.bootcamp.banca.cliente.services.ClientService;
import proyecto.bootcamp.banca.cliente.utils.AppClientUtils;
import reactor.adapter.rxjava.RxJava3Adapter;

@SpringBootTest
class ClienteApplicationTests {
	@Autowired
	private static Logger logger = LoggerFactory.getLogger(ClientService.class);
	@Autowired
	private Environment env;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientTypeRepository clientTypeRepository;
	@Test
	void contextLoads() {

	}
	@Test
	void testLog() {
		String url=env.getProperty("apis.account")+"client/ndoc/";
		logger.info("url accounts: "+url);

	}

	void testCreationClient(){

		ClientType clientTypePersonal= clientTypeRepository.findById("6466d4b6d90ed775c7863f8d").block();
		ClientType clientTypeEmpresarial= clientTypeRepository.findById("6466d4b7d90ed775c7863f8e").block();
		Client client= new Client(AppClientUtils.createNumber(),"DNI","Test1",clientTypePersonal);

		client= (Client)clientService.createClient(new InputClientDTO(client.getNDoc()
				,client.getTDoc()
				,"name: "+client.getNDoc()
				,client.getTypeClient().getId())).blockingGet();
	}
//	@Test
//	@RepeatedTest(value = 2)
	void testCreationClientIndex(){
		ClientType clientTypePersonal= clientTypeRepository.findById("6466d4b6d90ed775c7863f8d").block();
		ClientType clientTypeEmpresarial= clientTypeRepository.findById("6466d4b7d90ed775c7863f8e").block();
		Client client= new Client("06956020972756687298","DNI","Test1",clientTypePersonal);

		client= (Client)clientService.createClient(new InputClientDTO(client.getNDoc()
				,client.getTDoc()
				,"name: "+client.getNDoc()
				,client.getTypeClient().getId())).blockingGet();
	}



}
