package com.project.server.beans;

import com.project.server.enums.Type;

public class UserDto {
    private long id;
    private String userName;
    private String password;
    private Type userType;
    private String companyName;

    public UserDto(long id, String userName, String password, Type userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;

    }
    public UserDto(long id, String userName, String password, Type userType, String companyName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.companyName = companyName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
