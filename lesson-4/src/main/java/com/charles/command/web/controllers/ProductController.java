package com.charles.command.web.controllers;

import com.charles.command.commands.CreateProductCommand;
import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.ConcurrencyException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final Logger LOGGER = getLogger(ProductController.class);

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "{/id}",method = RequestMethod.POST)
    public void create(@PathVariable(value = "id") String id,
                       @RequestParam(value = "name",required = true) String name,
                       @RequestParam(value = "price",required = true) long price,
                       @RequestParam(value = "stock",required = true) int stock,
                       HttpServletResponse response) {
        LOGGER.debug("Adding Product [{}] '{}' {} * {}",id,name,price,stock);
        try {
            CreateProductCommand command = new CreateProductCommand(id,name,price * 100,stock);
            commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
        } catch (CommandExecutionException cex) {
            LOGGER.warn("Add Command FAILED with Message:{}",cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            if (null != cex.getCause()) {
                LOGGER.warn("Caused by:{} {}",cex.getCause().getClass().getName(),cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOGGER.warn("A duplicate product with the same Id[{}] already exists.",id);
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    }
}
