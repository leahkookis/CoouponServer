package com.project.server.entity;

import javax.persistence.*;


@Entity
@Table(name = "Customers")
public class Customer {




    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false  )
    private String address;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    public Customer() {
    }

    public Customer(User user, Long id, String name, String address, String phoneNumber) {
        this.user = user;
        this.id = user.getId();
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

//    public Long getUserId(User user){
//        return getUserId(user);
//    }


}
