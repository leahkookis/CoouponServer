package com.lea.server.controllers;

import com.lea.server.beans.CustomerDto;
import com.lea.server.entity.Customer;
import com.lea.server.logic.CustomerLogic;
import com.lea.server.utils.JWTUtils;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
@RestController
@RequestMapping("/customer")
public class CustomerController {
  private CustomerLogic customerLogic;

  @Autowired
  public CustomerController(CustomerLogic customerLogic) {
    this.customerLogic = customerLogic;
  }

  @PostMapping
  @Transactional
  public long createCustomer(@RequestBody Customer customer) throws ServerException {
    return customerLogic.createCustomer(customer);
  }

  @PutMapping
  public void updateCustomer(@RequestHeader String authorization, @RequestBody Customer customer) throws Exception {
    JWTUtils.validateToken(authorization);
    customerLogic.updateCustomer(customer);
  }

  @DeleteMapping("/{customerId}")
  public void removeCustomer(@RequestHeader String authorization, @PathVariable("customerId") long customerId) throws Exception {
    JWTUtils.validateToken(authorization);
    customerLogic.removeCustomer(customerId);
  }

  @GetMapping("/{customerId}")
  public CustomerDto getCustomerByCustomerID(@RequestHeader String authorization, @PathVariable("customerId") int customerId) throws Exception {
    JWTUtils.validateToken(authorization);
    return customerLogic.getCustomer(customerId);
  }

  @GetMapping
  public List <CustomerDto> getAllCustomers(@RequestHeader String authorization, @RequestParam("page") int page) throws Exception {
    JWTUtils.validateToken(authorization);
    return customerLogic.getAllCustomer(page);
  }




}
