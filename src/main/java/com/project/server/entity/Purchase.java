package com.project.server.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Purchases")
public class Purchase {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "time_stamp")
  private Date timeStamp;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = true)
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "coupon_id", nullable = false)
  private Coupon coupon;

  @Column(name = "is_buy")
  private boolean isBuy;

  public Purchase() {
  }

  public Purchase(long id, Date timeStamp, Customer customer, Coupon coupon, boolean isBuy) {
    this.id = id;
    this.timeStamp = timeStamp;
    this.customer = customer;
    this.coupon = coupon;
    this.isBuy = isBuy;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Coupon getCoupon() {
    return coupon;
  }

  public void setCoupon(Coupon coupon) {
    this.coupon = coupon;
  }

  public boolean getIsBuy() {
    return isBuy;
  }

  public void setIsBuy(boolean isBuy) {
    this.isBuy = isBuy;
  }
}
