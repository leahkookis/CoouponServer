package com.lea.server.controllers;

import com.lea.server.beans.CouponDto;
import com.lea.server.entity.Coupon;
import com.lea.server.logic.CouponLogic;
import com.lea.server.utils.JWTUtils;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    private CouponLogic couponLogic;

    @Autowired
    public CouponController(CouponLogic couponLogic) {
        this.couponLogic = couponLogic;
    }

    @PostMapping
    public long createCoupon(@RequestHeader String authorization,@RequestBody Coupon coupon) throws Exception {
        JWTUtils.validateToken(authorization);
        return couponLogic.createCoupon(coupon);
    }

    @PutMapping
    public void updateCoupon(@RequestHeader String authorization, @RequestBody Coupon coupon) throws Exception {
        JWTUtils.validateToken(authorization);
        couponLogic.updateCoupon(coupon);
    }

    @DeleteMapping("/{couponId}")
    public void removeCoupon(@RequestHeader String authorization, @PathVariable("couponId") long couponId) throws Exception {
        JWTUtils.validateToken(authorization);
        couponLogic.removeCoupon(couponId);
    }


    @GetMapping
    public List<CouponDto> getAllCoupons(@RequestParam("page") int page) throws ServerException {
        return couponLogic.getAllCoupons(page);
    }


        @GetMapping("/{couponId}")
    public CouponDto getCouponByCouponID(@PathVariable("couponId") int couponId) throws ServerException {
        return couponLogic.getCoupon(couponId);
    }

    @GetMapping("/byCompany")
    public List<CouponDto> getCouponsByCompanyID(@RequestParam("companyId") long companyId, @RequestParam("page") int page) throws ServerException {
        return couponLogic.getCouponsByCompanyID(companyId, page);
    }

    @GetMapping("/bycategory")
    public List<CouponDto> getCouponsByCategoryID(@RequestParam("categoryid") long categoryId) throws ServerException {
        return couponLogic.getCouponsByCategoryID(categoryId, 1);
    }


    @GetMapping("/byPrice")
    public List<CouponDto> getAllCouponsOrderByPrice() throws ServerException {
        return couponLogic.getAllCouponsOrderByPrice();
    }



    @GetMapping("/minPrice")
    public CouponDto getMinPriceCouponsByCategoryID(@RequestParam("categoryId") long categoryId) throws ServerException {
        return couponLogic.getMinPriceCouponsByCategoryID(categoryId);
    }
}

