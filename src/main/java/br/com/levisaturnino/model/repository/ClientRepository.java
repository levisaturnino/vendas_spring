package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {

    private static String INSERT = "insert into client (name) values (?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client){
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }
}
