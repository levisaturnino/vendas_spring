package br.com.levisaturnino.model.entity;

import br.com.levisaturnino.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column
    private StatusPedidoEnum status;

    @OneToMany( mappedBy = "order", fetch = FetchType.LAZY)
    private List<ItemOrder> orders;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dataOrder=" + dataOrder +
                ", total=" + total +
                '}';
    }
}
