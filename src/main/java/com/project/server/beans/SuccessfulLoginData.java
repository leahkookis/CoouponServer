package com.project.server.beans;


import com.project.server.enums.Type;

public class SuccessfulLoginData {

  private Long id;
  private Type userType;
  private Long companyId;




  public SuccessfulLoginData(Long id, Type userType, Long companyId) {
    this.id = id;
    this.userType = userType;
    this.companyId = companyId;
  }

  public Type getUserType() {
    return userType;
  }

  public void setUserType(Type userType) {
    this.userType = userType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  @Override
  public String toString() {
    return "SuccessfulLoginData{" +
            "id=" + id +
            ", userType='" + userType + '\'' +
            ", companyId='" + companyId + '\'' +
            '}';
  }
}
