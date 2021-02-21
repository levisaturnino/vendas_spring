package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    private static String INSERT         = "INSERT INTO client (name) VALUES (?)";
    private static String SELECT_ALL     = "SELECT * FROM Client";
    private static String UPDATE         = "UPDATE client SET name = ? WHERE id = ?";
    private static String DELETE         = "DELETE FROM client WHERE id = ?";
    private static String SELECT_BY_NAME = "SELECT * FROM Client WHERE name LIKE ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client){
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public Client update(Client client){
        jdbcTemplate.update(UPDATE, new Object[]{client.getName(),client.getId()});
        return client;
    }

    public void delete(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public void delete(Client client){
        jdbcTemplate.update(DELETE, new Object[]{client.getId()});
    }

    public List<Client> findByName(String name){
        return jdbcTemplate.query(SELECT_BY_NAME, new Object[]{"%"+name+"%"},getRowMapper());
    }

    public List<Client> getAll(){
        return jdbcTemplate.query(SELECT_ALL, getRowMapper());
    }

    private RowMapper<Client> getRowMapper() {
        return new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Client(resultSet.getInt("id"), resultSet.getString("name"));
            }
        };
    }
}
