package org.example.entity;

public class Transaction {
    private int amount;
    private String date;
    private String type;

    public Transaction(int amount, String date, String type) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public int getAmount() { return amount; }
    public String getDate() { return date; }
    public String getType() { return type; }
}
