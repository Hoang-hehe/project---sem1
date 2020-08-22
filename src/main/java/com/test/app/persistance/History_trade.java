package com.test.app.persistance;

import java.sql.Date;

public class History_trade {
    private int Trade_History_id;
    private int Account_id;
    private Date date_traded;
    private String type_trade;
    private int amount;

    public History_trade(int trade_History_id, int account_id, Date date_traded, String type_trade, int amount) {
        Trade_History_id = trade_History_id;
        Account_id = account_id;
        this.date_traded = date_traded;
        this.type_trade = type_trade;
        this.amount = amount;
    }

    public int getTrade_History_id() {
        return Trade_History_id;
    }

    public void setTrade_History_id(int trade_History_id) {
        Trade_History_id = trade_History_id;
    }

    public int getAccount_id() {
        return Account_id;
    }

    public void setAccount_id(int account_id) {
        Account_id = account_id;
    }

    public Date getDate_traded() {
        return date_traded;
    }

    public void setDate_traded(Date date_traded) {
        this.date_traded = date_traded;
    }

    public String getType_trade() {
        return type_trade;
    }

    public void setType_trade(String type_trade) {
        this.type_trade = type_trade;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    

    
}