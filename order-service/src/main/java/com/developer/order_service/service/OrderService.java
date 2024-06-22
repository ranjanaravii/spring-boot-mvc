package com.developer.order_service.service;

import com.developer.order_service.common.Constant;
import com.developer.order_service.config.WebClientConfig;
import com.developer.order_service.dto.InventoryResponse;
import com.developer.order_service.dto.OrderRequest;
import com.developer.order_service.dto.OrderedItemsDto;
import com.developer.order_service.model.Order;
import com.developer.order_service.model.OrderedItems;
import com.developer.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClientConfig webClientConfig;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderedItems> orderedItems = orderRequest.getOrderedItemsDtoList().stream()
                .map(this::mapToOrderedItems)
                .toList();
        order.setOrderedItems(orderedItems);
        List<String> skuCodes = order.getOrderedItems().stream().map(OrderedItems::getSkuCode).toList();

        //call inventory service to check if the items are available
        //if available, call product service to get the price
        //calculate the total price
        //save the order
        InventoryResponse[] inventoryResponses = webClientConfig.rxGetWebClient()
                .get()
                .uri("http://localhost:8084/inventory-service/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam(Constant.SKU_CODE, skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        if (inventoryResponses == null) {
            // Handle the case where inventoryResponses is null
            log.error("Inventory responses are null");
            return;
        }
        Boolean allProductIsAvailable = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isAvailable);
        if (Boolean.TRUE.equals(allProductIsAvailable)) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Items are not available, please try again later.");
        }
    }

    private OrderedItems mapToOrderedItems(OrderedItemsDto orderedItemsDto) {
        OrderedItems orderedItems = new OrderedItems();
        orderedItems.setSkuCode(orderedItemsDto.getSkuCode());
        orderedItems.setQuantity(orderedItemsDto.getQuantity());
        orderedItems.setPrice(orderedItemsDto.getPrice());
        return orderedItems;
    }
}
