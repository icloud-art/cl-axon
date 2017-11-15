package com.charles.common.domain;

public class OrderProduct {
    private String id;
    private String name;
    private long price;
    private int amount;

    public OrderProduct(String id,String name,long price,int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}



