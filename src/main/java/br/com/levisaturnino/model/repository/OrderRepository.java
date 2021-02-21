package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.Set;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Set<Order> findByClient(Client client);

    @Query("SELECT p FROM Order p left join fetch p.orders where p.id =:id")
    Optional<Order> findByIdFetchOrders(@PathVariable Integer id);
}
