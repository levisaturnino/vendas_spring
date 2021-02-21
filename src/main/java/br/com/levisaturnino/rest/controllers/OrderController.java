package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Product;
import br.com.levisaturnino.model.repository.ProductRepository;
import br.com.levisaturnino.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }
}
