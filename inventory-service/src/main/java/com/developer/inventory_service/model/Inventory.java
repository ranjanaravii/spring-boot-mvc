package com.developer.inventory_service.model;

import com.developer.inventory_service.common.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(Constant.SKU_CODE)
    private String skuCode;

    @JsonProperty(Constant.QUANTITY)
    private Integer quantity;
}
