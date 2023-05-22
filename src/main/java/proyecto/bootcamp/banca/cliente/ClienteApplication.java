package proyecto.bootcamp.banca.cliente;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import proyecto.bootcamp.banca.cliente.model.AccountConditions;
import proyecto.bootcamp.banca.cliente.model.Client;
import proyecto.bootcamp.banca.cliente.model.ClientType;
import proyecto.bootcamp.banca.cliente.model.CreditConditions;
import proyecto.bootcamp.banca.cliente.repository.ClientRepository;
import proyecto.bootcamp.banca.cliente.repository.ClientTypeRepository;

@SpringBootApplication
public class ClienteApplication

	{

	public static void main(String[] args) {

		SpringApplication.run(ClienteApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ClientTypeRepository repository, ClientRepository clientRepository){
		return args-> {
//			AccountConditions personal = new AccountConditions(2,1,1,1,true);
//			AccountConditions empresarial= new AccountConditions(-1,0,-1,0,false);
//			CreditConditions cpersonal= new CreditConditions(1);
//			CreditConditions cempresarial= new CreditConditions(-1);
//
//			ClientType clientTypePersonal= new ClientType("Personal",personal,cpersonal);
//			ClientType clientTypeEmpresarial= new ClientType("Empresarial",empresarial,cempresarial);
//			repository.insert(clientTypePersonal);
//			repository.insert(clientTypeEmpresarial);
//
//			Client clientePersonal=new Client("43232343","DNI","Victor Dennis",clientTypePersonal);
//			Client clienteEmpresarial=new Client("20143232343","RUC","JAvaDus Company",clientTypeEmpresarial);
//			clientRepository.insert(clientePersonal);
//			clientRepository.insert(clienteEmpresarial);


			System.out.println("Se insertaron las colecciones");
		};
	}

}
