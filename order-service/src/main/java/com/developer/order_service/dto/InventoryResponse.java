package com.developer.order_service.dto;

import com.developer.order_service.common.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    @JsonProperty(Constant.SKU_CODE)
    private String skuCode;

    @JsonProperty(Constant.IS_AVAILABLE)
    private boolean isAvailable;
}
