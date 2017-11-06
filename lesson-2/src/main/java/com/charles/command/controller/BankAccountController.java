package com.charles.command.controller;

import com.charles.command.commands.CreateAccountCommand;
import com.charles.command.commands.WithdrawMoneyCommand;
import com.charles.common.domain.AccountId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;


@RestController
@RequestMapping(value = "/bank")
public class BankAccountController {
    private static final Logger LOGGER = getLogger(BankAccountController.class);

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(method = RequestMethod.POST)
    public void create(){
        LOGGER.info("start");
        AccountId id = new AccountId();
        LOGGER.debug("Account id:{}",id.toString());
        commandGateway.send(new CreateAccountCommand(id,"Charles' Account",1000));
        commandGateway.send(new WithdrawMoneyCommand(id,500));
        commandGateway.send(new WithdrawMoneyCommand(id,300));
        commandGateway.send(new CreateAccountCommand(id,"Charles' Account",1000));
        commandGateway.send(new WithdrawMoneyCommand(id,500));
    }

}
