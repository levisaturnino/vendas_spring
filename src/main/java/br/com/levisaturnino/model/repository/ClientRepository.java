package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {

        @Query(value = "SELECT * FROM Client",nativeQuery = true)
        List<Client> getAll();

        @Query(value = "SELECT c FROM Client c WHERE c.name like '%:name%'")
        List<Client> findByName(@Param("name") String name);

        @Query(value = "UPDATE client SET name = :name WHERE id = :id",nativeQuery = true)
        Client update(@Param("name") String name,@Param("id") String id);

        @Query(value = "DELETE FROM client name = :name",nativeQuery = true)
        @Modifying
        Client deleteByName(@Param("name") String name);

        // QueryMethod
        List<Client> findByNameLike(String name);
        List<Client> findByNameOrIdOrderById(String name,Integer id);

        boolean existsByName(String name);
}
