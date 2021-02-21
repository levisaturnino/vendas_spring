package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.ItemOrder;
import br.com.levisaturnino.model.entity.Order;
import br.com.levisaturnino.rest.dto.InformationItemOrderDTO;
import br.com.levisaturnino.rest.dto.InformationOrderDTO;
import br.com.levisaturnino.rest.dto.OrderDTO;
import br.com.levisaturnino.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody OrderDTO orderDTO){
        Order order = service.save(orderDTO);
        return order.getId();
    }

    @GetMapping("{id}")
    public InformationOrderDTO getById(@PathVariable Integer id){
        return service.getOrderComplete(id)
                .map( order ->
                    convert(order)
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido não encontrado."));
    }

    private InformationOrderDTO convert(Order order) {

        return InformationOrderDTO.builder()
                .id(order.getId())
                .dataOrder(order.getDataOrder().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(order.getClient().getCpf())
                .nameClient(order.getClient().getName())
                .total(order.getTotal())
                .status(order.getStatus().name())
                .items(convert(order.getOrders()))
                .build();
    }


    private List<InformationItemOrderDTO> convert(List<ItemOrder> items) {

        if(CollectionUtils.isEmpty(items)){
            return Collections.emptyList();
        }

        return items
                .stream()
                .map(
                item -> InformationItemOrderDTO.builder()
                        .descriptionProduct(item.getProduct().getDescription())
                        .price(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .build()
        ).collect(Collectors.toList());
    }
}
