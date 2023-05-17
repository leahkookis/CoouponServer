package com.lea.server.logic;


import com.lea.server.beans.CustomerDto;
import com.lea.server.constanse.Consts;
import com.lea.server.dal.ICustomerDal;
import com.lea.server.entity.Customer;
import com.lea.server.enums.ErrorType;
import com.lea.server.enums.Type;
import com.lea.server.utils.ServerException;
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