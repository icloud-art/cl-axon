package com.charles.lesson1;

import com.charles.command.aggregates.BankAccount;
import com.charles.command.commands.CreateAccountCommand;
import com.charles.command.commands.WithdrawMoneyCommand;
import com.charles.common.domain.AccountId;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class Lesson1Application {
	private static final Logger LOGGER = getLogger(Lesson1Application.class);

	public static void main(String[] args) {

		Configuration configuration = DefaultConfigurer.defaultConfiguration()
				.configureAggregate(BankAccount.class)
				.configureEmbeddedEventStore(c ->new InMemoryEventStorageEngine())
				.buildConfiguration();
		configuration.start();
		AccountId id = new AccountId();
		configuration.commandGateway().send(new CreateAccountCommand(id,"Charles",1000));
		configuration.commandGateway().send(new WithdrawMoneyCommand(id,500));
		configuration.commandGateway().send(new WithdrawMoneyCommand(id,500));
	}
}
