package com.charles.learn.command.commands;

import com.charles.learn.common.domain.AccountId;

public class CreateAccountCommand {
    private AccountId accountId;
    private String accountName;
    private long amount;

    public CreateAccountCommand(AccountId accountId, String accountName, long amount) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.amount = amount;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public long getAmount() {
        return amount;
    }
}
