package br.com.levisaturnino.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationItemOrderDTO {

    private String descriptionProduct;
    private BigDecimal price;
    private Integer quantity;
}
