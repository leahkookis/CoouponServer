package com.lea.server.dal;


import com.lea.server.beans.PurchaseDto;
import com.lea.server.entity.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPurchaseDal extends CrudRepository<Purchase, Long> {

   @Query("SELECT new com.lea.server.beans.PurchaseDto(pur.id, pur.customer.name, pur.coupon.price, pur.coupon.name, pur.timeStamp, pur.coupon.category.name, pur.coupon.company.name,pur.isBuy)" +
           "FROM Purchase pur JOIN Coupon coup ON pur.coupon.id = coup.id " +
           "JOIN Customer cus ON pur.customer.id = cus.id WHERE pur.id = :id")
   PurchaseDto findById(@Param("id")long id);

  @Query("SELECT new com.lea.server.beans.PurchaseDto(pur.id, pur.customer.name, pur.coupon.price, pur.coupon.name, pur.timeStamp, pur.coupon.category.name, pur.coupon.company.name,pur.isBuy)" +
            "FROM Purchase pur JOIN Coupon coup ON pur.coupon.id = coup.id " +
            "JOIN Customer cus ON pur.customer.id = cus.id")
   List<PurchaseDto> findAll(Pageable pageable);

    @Query("SELECT new com.lea.server.beans.PurchaseDto(pur.id, pur.customer.name, pur.coupon.price, pur.coupon.name, pur.timeStamp, pur.coupon.category.name, pur.coupon.company.name,pur.isBuy)" +
            "FROM Purchase pur JOIN Coupon coup ON pur.coupon.id = coup.id " +
            "JOIN Customer cus ON pur.customer.id = cus.id WHERE pur.customer.id = :customerId")
    List<PurchaseDto> getPurchasesByCustomerID(@Param("customerId") long customerId, Pageable pageable);

    @Query("SELECT new com.lea.server.beans.PurchaseDto(pur.id, pur.customer.name, pur.coupon.price, pur.coupon.name, pur.timeStamp, pur.coupon.category.name, pur.coupon.company.name ,pur.isBuy)" +
            "FROM Purchase pur JOIN Coupon coup ON pur.coupon.id = coup.id " +
            "JOIN Customer cus ON pur.customer.id = cus.id WHERE pur.coupon.company.id = :companyId")
    List<PurchaseDto> getPurchasesByCompanyID(@Param("companyId") long companyId, Pageable pageable);

    @Query("UPDATE Purchase pur set isBuy = true where pur.id in(purchaseIds)")
    void updateCouponsToBuy(Integer[] purchaseIds);

}

