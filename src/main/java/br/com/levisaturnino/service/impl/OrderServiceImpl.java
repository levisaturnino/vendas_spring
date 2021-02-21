package br.com.levisaturnino.service.impl;

import br.com.levisaturnino.model.repository.OrderRepository;
import br.com.levisaturnino.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }
}
