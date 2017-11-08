package com.charles.learn.common.events;

import com.charles.learn.common.domain.AccountId;

public class MoneyWithDrawnEvent {
    private AccountId accountId;
    private long amount;

    public  MoneyWithDrawnEvent(AccountId accountId, long amount) {
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
