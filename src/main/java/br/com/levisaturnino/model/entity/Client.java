package br.com.levisaturnino.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "Nome é obrigatório")
    private String name;

    @Column(length = 14)
    @NotEmpty(message = "CPF é obrigatório")
    @CPF(message = "Informe o CPF válido")
    private String cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "client")
    private Set<Order> orders = new HashSet<Order>();

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
