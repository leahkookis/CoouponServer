package com.project.server.entity;

import com.project.server.enums.Type;

import javax.persistence.*;


@Entity
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_name", unique = true,nullable = false  )
    private String userName;

    @Column(name = "password",nullable = false  )
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = true)
    private Type userType;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    public User() {

    }

    public User(Long id, String userName, String password, Type userType, Company company) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Type getUserType() {
        return userType;
    }

    public void setUserType(Type userType) {
        this.userType = userType;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", company=" + company +
                '}';
    }
}
