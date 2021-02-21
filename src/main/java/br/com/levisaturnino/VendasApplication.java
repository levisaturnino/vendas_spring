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
    public CommandLineRunner commandLineRunner(@Autowired ClientRepository clientRepository){
        return args -> {
            Client client = new Client("Levi");
            clientRepository.save(client);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }
}
