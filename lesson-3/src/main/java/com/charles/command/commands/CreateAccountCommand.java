package com.charles.command.commands;


import com.charles.common.domain.AccountId;

/**
 * Created by Edison Xu on 2017/3/7.
 */
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
