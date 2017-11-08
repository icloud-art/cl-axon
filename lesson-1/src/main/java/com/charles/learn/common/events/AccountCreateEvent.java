package com.charles.learn.common.events;

import com.charles.learn.common.domain.AccountId;

public class AccountCreateEvent {

    private AccountId accountId;
    private String accountName;
    private long amount;

    public AccountCreateEvent(AccountId accountId, String accountName, long amount) {
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