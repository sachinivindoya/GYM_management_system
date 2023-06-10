package com.gym.entity;

public class Payment implements SuperEntity{
    private int PaymentId;
    private String MemberId;
    private String Description;
    private String Date;
    private double Amount;

    @Override
    public String toString() {
        return "Payment{" +
                "PaymentId=" + PaymentId +
                ", MemberId='" + MemberId + '\'' +
                ", Description='" + Description + '\'' +
                ", Date='" + Date + '\'' +
                ", Amount=" + Amount +
                '}';
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public String getMemberId() {
        return MemberId;
    }

    public void setMemberId(String memberId) {
        MemberId = memberId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Payment() {
    }

    public Payment(int paymentId, String memberId, String description, String date, double amount) {
        PaymentId = paymentId;
        MemberId = memberId;
        Description = description;
        Date = date;
        Amount = amount;
    }



}
