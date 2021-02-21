package br.com.levisaturnino.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationOrderDTO {

    private Integer id;
    private String cpf;
    private String nameClient;
    private BigDecimal total;
    private String  dataOrder;
    private List<InformationItemOrderDTO> items;

}
