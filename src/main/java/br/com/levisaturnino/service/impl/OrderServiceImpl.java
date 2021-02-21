package br.com.levisaturnino.service.impl;

import br.com.levisaturnino.enums.StatusPedidoEnum;
import br.com.levisaturnino.exception.BusinessRuleException;
import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.entity.ItemOrder;
import br.com.levisaturnino.model.entity.Order;
import br.com.levisaturnino.model.entity.Product;
import br.com.levisaturnino.model.repository.ClientRepository;
import br.com.levisaturnino.model.repository.ItemOrderRepository;
import br.com.levisaturnino.model.repository.OrderRepository;
import br.com.levisaturnino.model.repository.ProductRepository;
import br.com.levisaturnino.rest.dto.ItemOrderDTO;
import br.com.levisaturnino.rest.dto.OrderDTO;
import br.com.levisaturnino.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ItemOrderRepository itemOrderRepository;

    @Override
    @Transactional
    public Order save(OrderDTO orderDTO) {
        Integer idClient = orderDTO.getClient();
        Client client =  clientRepository
                .findById(idClient)
                .orElseThrow(() -> new BusinessRuleException("Código de cliente inválido."));

        Order order = new Order();
        order.setTotal(orderDTO.getTotal());
        order.setDataOrder(LocalDate.now());
        order.setClient(client);
        order.setStatus(StatusPedidoEnum.REALIAZADO);

        List<ItemOrder> itemOrders =  convertItems(order, orderDTO.getItems());

        orderRepository.save(order);
        itemOrderRepository.saveAll(itemOrders);

        order.setOrders(itemOrders);

        return order;
    }

    @Override
    public Optional<Order> getOrderComplete(Integer id) {
        Optional<Order> order = orderRepository.findByIdFetchOrders(id);
        return order;
    }

    private List<ItemOrder> convertItems(Order order,List<ItemOrderDTO> items){

        if(items.isEmpty()){
            throw new BusinessRuleException("Não é possivel realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduct = dto.getProduct();
                    Product product = productRepository
                            .findById(idProduct)
                            .orElseThrow(() -> new BusinessRuleException("Código de product inválido."));
                    ItemOrder itemOrder = new ItemOrder();
                    itemOrder.setQuantity(dto.getQuantity());
                    itemOrder.setOrder(order);
                    itemOrder.setProduct(product);
                    return itemOrder;
                }).collect(Collectors.toList());
    }
}
