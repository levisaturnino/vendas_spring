package br.com.levisaturnino.service;

import br.com.levisaturnino.enums.StatusOrderEnum;
import br.com.levisaturnino.model.entity.Order;
import br.com.levisaturnino.rest.dto.OrderDTO;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO orderDTO);

    Optional<Order> getOrderComplete(Integer id);

    void updateStatus(Integer id, StatusOrderEnum statusPedidoEnum);
}
