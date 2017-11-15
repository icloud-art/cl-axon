package com.charles.common.events;

import com.charles.common.domain.OrderId;
import com.charles.common.domain.OrderProduct;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.Map;

public class OrderCreateEvent {
    @TargetAggregateIdentifier
    private OrderId orderId;
    private String username;
    private Map<String,OrderProduct> products;

    public OrderCreateEvent(OrderId orderId,String username,Map<String ,OrderProduct> products) {
        this.orderId = orderId;
        this.username = username;
        this.products = products;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, OrderProduct> getProducts() {
        return products;
    }
}
