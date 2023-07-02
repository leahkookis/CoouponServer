package com.lea.server.beans;


import java.util.Date;

public class PurchaseDto {
    private long id;
    private String name;
    private float couponPrice;
    private String couponName;
    private Date timeStamp;
    private String categoryName;
    private String companyName;
    private int amount;

    public PurchaseDto(long id, String name, float couponPrice, String couponName, Date timeStamp, String categoryName, String companyName, int amount) {
        this.id = id;
        this.name = name;
        this.couponPrice = couponPrice;
        this.couponName = couponName;
        this.timeStamp = timeStamp;
        this.categoryName = categoryName;
        this.companyName = companyName;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(float couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
