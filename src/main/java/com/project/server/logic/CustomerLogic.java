package com.project.server.logic;


import com.project.server.utils.ServerException;
import com.project.server.beans.CustomerDto;
import com.project.server.constanse.Consts;
import com.project.server.dal.ICustomerDal;
import com.project.server.entity.Customer;
import com.project.server.enums.ErrorType;
import com.project.server.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLogic {
  private ICustomerDal customerDal;
  private UserLogic userLogic;

  @Autowired
  public CustomerLogic(ICustomerDal customerDal, UserLogic userLogic) {
    this.customerDal = customerDal;
    this.userLogic = userLogic;
  }

  public long createCustomer(Customer customer) throws ServerException {
    CustomerValidation(customer);
    customer.getUser().setUserType(Type.customer);
    customerDal.save(customer);
    return customer.getUser().getId();
  }

  public void updateCustomer(Customer customer) throws ServerException {
    CustomerValidation(customer);
    customerDal.save(customer);
  }

  public void removeCustomer(long customerId) throws ServerException {
    customerDal.deleteById(customerId);
  }

  public CustomerDto getCustomer(long customerID) throws ServerException {
   return customerDal.findById(customerID);
  }

  public List<CustomerDto> getAllCustomer(int page) throws ServerException {
    Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
    return customerDal.findAll(pageable);
  }

  private void CustomerValidation(Customer customer) throws ServerException {
    userLogic.UserValidation(customer.getUser());
    if (customer.getPhoneNumber() !=null && customer.getPhoneNumber().length() > 10) {
      throw new ServerException(ErrorType.INVALID_PHONE_NUMBER);
    }

  }
}