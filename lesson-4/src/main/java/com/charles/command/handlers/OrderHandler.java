package com.charles.command.handlers;

import com.charles.command.aggregates.OrderAggregate;
import com.charles.command.aggregates.ProductAggregate;
import com.charles.command.commands.CreateOrderCommand;
import com.charles.common.domain.OrderProduct;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.Aggregate;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventhandling.EventBus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class OrderHandler {
    private static final Logger LOGGER = getLogger(OrderHandler.class);

    @Autowired
    private Repository<OrderAggregate> repository;

    @Autowired
    private Repository<ProductAggregate> productAggregateRepository;

    @Autowired
    private EventBus eventBus;

    @CommandHandler
    public void handle(CreateOrderCommand command) throws Exception {
        Map<String,OrderProduct> products = new HashMap<>();
        command.getProducts().forEach((productId,number)->{
            LOGGER.debug("Loading product infomation with productId:{}",productId);
            Aggregate<ProductAggregate> aggregate = productAggregateRepository.load(productId);
            products.put(productId,new OrderProduct(productId,
                    aggregate.invoke(productAggregate -> productAggregate.getName()),
                    aggregate.invoke(productAggregate -> productAggregate.getPrice()),number));
        });
        repository.newInstance(()->new OrderAggregate(command.getOrderId(),command.getUsername(),products));
    }
}
