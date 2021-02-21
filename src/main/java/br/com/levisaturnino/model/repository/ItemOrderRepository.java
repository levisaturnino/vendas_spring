package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.entity.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemOrderRepository extends JpaRepository<ItemOrder,Integer> {

}
