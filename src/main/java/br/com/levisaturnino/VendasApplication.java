package br.com.levisaturnino;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientRepository clientRepository){
        return args -> {
            Client client = new Client();
            client.setName("Levi Saturnino");
            clientRepository.save(client);

            clientRepository.save(new Client("Saturnino"));

            List<Client> clients = clientRepository.getAll();
            clients.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}
