package com.project.server.dal;

import com.project.server.beans.CouponDto;
import com.project.server.entity.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.*;
import java.util.List;

public interface ICouponDal extends CrudRepository<Coupon, Long> {

    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name,coup.category.id, coup.company.name,coup.company.id, coup.amount) " +
            "FROM Coupon coup JOIN Category cat ON coup.category.id = cat.id " +
            "JOIN Company com ON coup.company.id = com.id WHERE coup.id= :id")
    CouponDto findById(@Param("id") long id);

    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name,coup.category.id, coup.company.name,coup.company.id, coup.amount)" +
           "FROM Coupon coup JOIN Category cat ON coup.category.id = cat.id " +
            "JOIN Company com ON coup.company.id = com.id WHERE coup.company.id= :companyId")
    List<CouponDto> getCouponsByCompanyID(@Param("companyId") long companyId, Pageable pageable);

    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name,coup.category.id, coup.company.name,coup.company.id, coup.amount)" +
            "FROM Coupon coup JOIN Category cat ON coup.category.id = cat.id " +
            "JOIN Company com ON coup.company.id = com.id WHERE coup.category.id = :categoryId")
    List<CouponDto> getCouponsByCategoryID(@Param("categoryId") long categoryId, Pageable pageable);

    //לשאול את אבי
//    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name, coup.company.name, coup.amount) " +
//            "FROM Coupon coup JOIN Company com ON coup.company.id = com.id " +
//            "JOIN Category cat ON coup.category.id = cat.id WHERE coup.category.id = :categoryId")
//    CouponDto getMinPriceCouponsByCategoryID(@Param("categoryId") long categoryId);

    @Query( "SELECT count(coup.amount)>0 FROM Coupon coup  WHERE coup.id= :couponId and coup.amount > 0")
    boolean isCouponInStock(@Param("couponId") long couponId);

    @Query("SELECT count(coup.id)>0 FROM Coupon coup  WHERE coup.id= :couponId AND coup.endDate BETWEEN :startDate AND :endDate")
    boolean isCouponValidDate(@Param("couponId") long couponId, @Param("startDate")Date startDate,@Param("endDate")Date endDate);

    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name,coup.category.id, coup.company.name,coup.company.id, coup.amount) " +
            "FROM Coupon coup JOIN Category cat ON coup.category.id = cat.id " +
            "JOIN Company com ON coup.company.id = com.id order by coup.price")
    List<CouponDto> getAllCouponsOrderByPrice();

    @Query("SELECT new com.sari.server.beans.CouponDto(coup.id, coup.name, coup.price, coup.description, coup.startDate, coup.endDate, coup.category.name,coup.category.id, coup.company.name,coup.company.id, coup.amount) " +
            "FROM Coupon coup JOIN Category cat ON coup.category.id = cat.id " +
            "JOIN Company com ON coup.company.id = com.id")
    List<CouponDto> getAllCoupons(Pageable pageable);
}



