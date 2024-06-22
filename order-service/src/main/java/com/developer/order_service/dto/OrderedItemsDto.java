package com.developer.order_service.dto;

import com.developer.order_service.common.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedItemsDto {

    @JsonProperty(Constant.ID)
    private Long id;

    @JsonProperty(Constant.SKU_CODE)
    private String skuCode;

    @JsonProperty(Constant.QUANTITY)
    private Integer quantity;

    @JsonProperty(Constant.PRICE)
    private BigDecimal price;
}
