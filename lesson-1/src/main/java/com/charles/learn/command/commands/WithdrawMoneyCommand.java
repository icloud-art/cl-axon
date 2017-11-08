package com.charles.learn.command.commands;

import com.charles.learn.common.domain.AccountId;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class WithdrawMoneyCommand {
    @TargetAggregateIdentifier
    private AccountId accountId;
    private long amount;

    public WithdrawMoneyCommand(AccountId accountId, long amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public long getAmount() {
        return amount;
    }
}