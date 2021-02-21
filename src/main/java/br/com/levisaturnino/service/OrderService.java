package br.com.levisaturnino.service;

import br.com.levisaturnino.model.entity.Order;
import br.com.levisaturnino.rest.dto.OrderDTO;

public interface OrderService {

    Order save(OrderDTO orderDTO);
}
