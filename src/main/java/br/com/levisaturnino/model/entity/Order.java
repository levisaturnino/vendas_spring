package br.com.levisaturnino.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "data_order")
    private LocalDate dataOrder;

    @Column(length = 20, precision = 2)
    private BigDecimal total;

    @OneToMany( mappedBy = "order")
    private Set<ItemOrder> orders;

    public Set<ItemOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ItemOrder> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDataOrder() {
        return dataOrder;
    }

    public void setDataOrder(LocalDate dataOrder) {
        this.dataOrder = dataOrder;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
