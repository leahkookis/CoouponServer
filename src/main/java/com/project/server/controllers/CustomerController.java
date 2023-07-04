package com.project.server.controllers;

import com.project.server.beans.CustomerDto;
import com.project.server.logic.CustomerLogic;
import com.project.server.utils.JWTUtils;
import com.project.server.utils.ServerException;
import com.project.server.entity.Customer;
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
