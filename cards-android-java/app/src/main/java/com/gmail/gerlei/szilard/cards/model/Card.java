package com.gmail.gerlei.szilard.cards.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

@SuppressWarnings("unused")
public class Card implements Parcelable {

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    @SerializedName("cardId")
    private int id;
    @SerializedName("issuer")
    private String issuer;
    @SerializedName("cardNumber")
    private String cardNumber;
    @SerializedName("expirationDate")
    private String expirationDate;
    @SerializedName("cardHolderName")
    private String cardHolderName;
    @SerializedName("friendlyName")
    private String friendlyName;
    @SerializedName("currency")
    private String currency;
    @SerializedName("cvv")
    private int cvv;
    @SerializedName("availableBalance")
    private int availableBalance;
    @SerializedName("currentBalance")
    private int currentBalance;
    @SerializedName("minPayment")
    private int minPayment;
    @SerializedName("dueDate")
    private Date dueDate;
    @SerializedName("reservations")
    private int reservations;
    @SerializedName("balanceCarriedOverFromLastStatement")
    private int balanceCarriedOverFromLastStatement;
    @SerializedName("spendingsSinceLastStatement")
    private int spendingsSinceLastStatement;
    @SerializedName("yourLastRepayment")
    private Date yourLastRepayment;
    @SerializedName("accountDetails")
    private AccountDetails accountDetails;
    @SerializedName("cardImage")
    private int cardImage;

    public Card(int id, String issuer, String cardNumber, String expirationDate, String cardHolderName, String friendlyName, String currency, int cvv, int availableBalance,
                int currentBalance, int minPayment, Date dueDate, int reservations, int balanceCarriedOverFromLastStatement, int spendingsSinceLastStatement, Date yourLastRepayment,
                AccountDetails accountDetails) {
        this.id = id;
        this.issuer = issuer;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.friendlyName = friendlyName;
        this.currency = currency;
        this.cvv = cvv;
        this.availableBalance = availableBalance;
        this.currentBalance = currentBalance;
        this.minPayment = minPayment;
        this.dueDate = dueDate;
        this.reservations = reservations;
        this.balanceCarriedOverFromLastStatement = balanceCarriedOverFromLastStatement;
        this.spendingsSinceLastStatement = spendingsSinceLastStatement;
        this.yourLastRepayment = yourLastRepayment;
        this.accountDetails = accountDetails;
    }

    private Card(Parcel in) {
        id = in.readInt();
        issuer = in.readString();
        cardNumber = in.readString();
        expirationDate = in.readString();
        cardHolderName = in.readString();
        friendlyName = in.readString();
        currency = in.readString();
        cvv = in.readInt();
        availableBalance = in.readInt();
        currentBalance = in.readInt();
        minPayment = in.readInt();
        reservations = in.readInt();
        balanceCarriedOverFromLastStatement = in.readInt();
        spendingsSinceLastStatement = in.readInt();
        cardImage = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(issuer);
        parcel.writeString(cardNumber);
        parcel.writeString(expirationDate);
        parcel.writeString(cardHolderName);
        parcel.writeString(friendlyName);
        parcel.writeString(currency);
        parcel.writeInt(cvv);
        parcel.writeInt(availableBalance);
        parcel.writeInt(currentBalance);
        parcel.writeInt(minPayment);
        parcel.writeInt(reservations);
        parcel.writeInt(balanceCarriedOverFromLastStatement);
        parcel.writeInt(spendingsSinceLastStatement);
        parcel.writeInt(cardImage);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getMinPayment() {
        return minPayment;
    }

    public void setMinPayment(int minPayment) {
        this.minPayment = minPayment;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }

    public int getBalanceCarriedOverFromLastStatement() {
        return balanceCarriedOverFromLastStatement;
    }

    public void setBalanceCarriedOverFromLastStatement(int balanceCarriedOverFromLastStatement) {
        this.balanceCarriedOverFromLastStatement = balanceCarriedOverFromLastStatement;
    }

    public int getSpendingsSinceLastStatement() {
        return spendingsSinceLastStatement;
    }

    public void setSpendingsSinceLastStatement(int spendingsSinceLastStatement) {
        this.spendingsSinceLastStatement = spendingsSinceLastStatement;
    }

    public Date getYourLastRepayment() {
        return yourLastRepayment;
    }

    public void setYourLastRepayment(Date yourLastRepayment) {
        this.yourLastRepayment = yourLastRepayment;
    }

    public AccountDetails getAccountDetails() {
        return accountDetails;
    }

    public void setAccountDetails(AccountDetails accountDetails) {
        this.accountDetails = accountDetails;
    }

    public int getCardImage() {
        return cardImage;
    }

    public void setCardImage(int cardImage) {
        this.cardImage = cardImage;
    }
}
