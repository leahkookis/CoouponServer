package com.lea.server.beans;


import java.util.Date;

public class CouponDto {
    private long id;
    private String name;
    private float price;
    private String description;
    private Date startDate;
    private Date endDate;
    private String categoryName;
    private String companyName;
    private int amount;

    public CouponDto(long id, String name, float price, String description, Date startDate, Date endDate, String categoryName, String companyName, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    @Override
    public String toString() {
        return "CouponDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", categoryName='" + categoryName + '\'' +
                ", companyName=" + companyName +
                ", amount=" + amount +
                '}';
    }
}
