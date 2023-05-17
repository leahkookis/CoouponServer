package com.lea.server.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Coupons")
public class Coupon {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private float price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="company_id", nullable = false )
    private Company company;

    @Column(name="amount", nullable = false )
    private int amount;

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
    private List<Purchase> purchases;

    public Coupon() {
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CouponEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", startingDate=" + startDate +
                ", endingDate=" + endDate +
                ", category='" + category + '\'' +
                ", companyId=" + company +
                ", amount=" + amount +
                '}';
    }
}
