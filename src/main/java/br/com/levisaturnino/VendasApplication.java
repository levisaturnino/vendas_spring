package br.com.levisaturnino;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.entity.Order;
import br.com.levisaturnino.model.repository.ClientRepository;
import br.com.levisaturnino.model.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository,@Autowired OrderRepository orderRepository){
        return args -> {

            System.out.println("Save Client.");
            Client client = new Client();
            client.setName("Levi Saturnino");
            clientRepository.save(client);

            Order  order = new Order();
            order.setClient(client);
            order.setDataOrder( LocalDate.now() );
            order.setTotal(BigDecimal.valueOf(100));

            orderRepository.save(order);

        /*   Client client1 = clientRepository.findClientFetch(client.getId());

            System.out.println(client1);
            System.out.println(client1.getOrders());*/

            orderRepository.findByClient(client).forEach(System.out::println);


           /* Client client = new Client();
            client.setName("Levi Saturnino");
            clientRepository.save(client);

            clientRepository.save(new Client("Saturnino"));

            List<Client> clients = clientRepository.getAll();
            clients.forEach(System.out::println);

            clients.forEach( cli  ->{
                cli.setName(cli.getName() + " update.");
               // clientRepository.update(cli);
            });

            System.out.println("Init find client");
            clientRepository.findByName("Satu").forEach(System.out::println);
            System.out.println("End find client");

            System.out.println("Delete client");
            clients.forEach( cli  ->{
                clientRepository.delete(cli);
            });

            clients = clientRepository.getAll();
            if(clients.isEmpty()){
                System.out.println("No customers found.");
            }else{
                clients.forEach(System.out::println);
            }*/
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}
