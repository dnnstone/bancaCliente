package proyecto.bootcamp.banca.cliente.dao;

import io.reactivex.rxjava3.core.Flowable;
import org.springframework.stereotype.Component;
import proyecto.bootcamp.banca.cliente.dto.Customer;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerComponent {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomer(){
        return IntStream.rangeClosed(100,150)
                .peek(CustomerComponent::sleepExecution)
                .peek(i->System.out.println("processing count"+i))
                .mapToObj(i->new Customer(i,"Customer"+1))
                .collect(Collectors.toList());
    }
    public Flowable<Customer> getAllCustomerStream(){
        return Flowable.range(1,50)
                .delay(500, TimeUnit.MILLISECONDS)
//                .delaySubscription(500, TimeUnit.MILLISECONDS)
                .doOnNext(i->System.out.println("processingStream count"+i))
                .map(i->new Customer(i,"Client "+i));

//        return IntStream.rangeClosed(100,150)
//                .peek(CustomerComponent::sleepExecution)
//                .peek(i->System.out.println("processing count"+i))
//                .mapToObj(i->new Customer(i,"Customer"+1))
//                .collect(Collectors.toList());
    }
}
