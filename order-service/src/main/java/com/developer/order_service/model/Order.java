package com.developer.order_service.model;

import com.developer.order_service.common.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(Constant.ID)
    private Long id;

    @JsonProperty(Constant.ORDER_NUMBER)
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderedItems> orderedItems;
}
