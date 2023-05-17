package com.lea.server.dal;

import com.lea.server.beans.CustomerDto;
import com.lea.server.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ICustomerDal extends CrudRepository<Customer, Long> {

  @Query("SELECT new com.lea.server.beans.CustomerDto(cus.id, cus.name, cus.address, cus.phoneNumber) FROM Customer cus WHERE cus.id = :id")
  CustomerDto findById(@Param("id") long id);

  @Query("SELECT new com.lea.server.beans.CustomerDto(cus.id, cus.name, cus.address, cus.phoneNumber) FROM Customer cus")
  List<CustomerDto> findAll(Pageable pageable);
}