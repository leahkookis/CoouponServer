package com.lea.server.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Coupon> coupons;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
