package com.gmail.gerlei.szilard.cards.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class AccountDetails {

    @SerializedName("accountLimit")
    private int accountLimit;
    @SerializedName("accountNumber")
    private String accountNumber;

    public AccountDetails(int accountLimit, String accountNumber) {
        this.accountLimit = accountLimit;
        this.accountNumber = accountNumber;
    }

    public int getAccountLimit() {
        return accountLimit;
    }

    public void setAccountLimit(int accountLimit) {
        this.accountLimit = accountLimit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
