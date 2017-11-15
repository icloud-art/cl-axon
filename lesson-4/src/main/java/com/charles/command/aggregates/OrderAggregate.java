package com.charles.command.aggregates;

import com.charles.common.domain.OrderId;
import com.charles.common.domain.OrderProduct;
import com.charles.common.events.OrderCreateEvent;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.core.annotation.Order;

import java.util.Map;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private OrderId id;
    private String username;
    private double payment;

    @AggregateMember
    private Map<String,OrderProduct> products;
    public OrderAggregate(){
        super();
    }

    public OrderAggregate(OrderId id,String username,Map<String,OrderProduct> products){
        apply(new OrderCreateEvent(id,username,products));
    }

    public OrderId getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Map<String, OrderProduct> getProducts() {
        return products;
    }

    @EventHandler
    public void on(OrderCreateEvent event) {
        this.id = event.getOrderId();
        this.username = event.getUsername();
        this.products = event.getProducts();
        computePrice();
    }

    private void computePrice() {
        products.forEach((id,product)->{
            payment += product.getPrice() * product.getAmount();
        });
    }
    public double getPayment() {
        return payment/100;
    }

    public void addProduct(OrderProduct product) {
        this.products.put(product.getId(),product);
        payment += product.getPrice() * product.getAmount();
    }

    public void removeProduct(String productId) {
        OrderProduct product = this.products.remove(productId);
        payment -= payment - product.getPrice() * product.getAmount();
    }
}























