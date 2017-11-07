package com.charles.command.aggregates;

import com.charles.command.commands.CreateAccountCommand;
import com.charles.command.commands.WithdrawMoneyCommand;
import com.charles.common.domain.AccountId;
import com.charles.common.events.AccountCreateEvent;
import com.charles.common.events.MoneyWithDrawnEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;

import java.math.BigDecimal;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import static org.slf4j.LoggerFactory.getLogger;

@Aggregate(repository = "accountRepository")
@Entity
public class BankAccount {

    private static final Logger LOGGER = getLogger(BankAccount.class);

    @AggregateIdentifier
    private AccountId accountId;
    private String accountName;
    private BigDecimal balance;

    public BankAccount(){
        super();
    }

    @CommandHandler
    public BankAccount(CreateAccountCommand command) {
        LOGGER.debug("Construct a new BankAcount");
        apply(new AccountCreateEvent(command.getAccountId(),command.getAccountName(),command.getAmount()));
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command) {
        apply(new MoneyWithDrawnEvent(command.getAccountId(),command.getAmount()));
    }

    @EventHandler
    public void on(AccountCreateEvent event) {
        this.accountId = event.getAccountId();
        this.accountName = event.getAccountName();
        this.balance = new BigDecimal(event.getAmount());
        LOGGER.info("Account {} is created with balance {}",accountId,this.balance);
    }

    @EventHandler
    public void on(MoneyWithDrawnEvent event) {
        BigDecimal result = this.balance.subtract(new BigDecimal(event.getAmount()));
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            LOGGER.error("Cannot withdraw more money than the balance!");
        } else {
            this.balance = result;
            LOGGER.info("Withdraw {} from account {},balance result:{}",event.getAmount(),accountId,balance);
        }
    }

    @Id
    public String getAccountId(){
        return accountId.toString();
    }

    public void setAccountId(String accountId) {
        this.accountId = new AccountId(accountId);
    }

    @Column
    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column
    public BigDecimal getBalance(){
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}



















