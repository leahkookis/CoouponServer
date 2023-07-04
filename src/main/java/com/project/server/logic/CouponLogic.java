package com.project.server.logic;

import com.project.server.utils.ServerException;
import com.project.server.beans.CouponDto;
import com.project.server.constanse.Consts;
import com.project.server.dal.ICouponDal;
import com.project.server.entity.Coupon;
import com.project.server.enums.ErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CouponLogic {

  private ICouponDal couponDal;
  private CategoryLogic categoryLogic;
  private CompanyLogic companyLogic;

  @Autowired
  public CouponLogic(ICouponDal couponDal, CategoryLogic categoryLogic, CompanyLogic companyLogic) {
    this.couponDal = couponDal;
    this.categoryLogic = categoryLogic;
    this.companyLogic = companyLogic;
  }

  public long createCoupon(Coupon coupon) throws ServerException {
    CouponValidation(coupon);
    couponDal.save(coupon);
    return coupon.getId();
  }

  private void CouponValidation(Coupon coupon) throws ServerException {
    if (coupon.getAmount() <= 0) {
      throw new ServerException(ErrorType.INVALID_FIELD);
    }
    if (coupon.getStartDate().after(coupon.getEndDate()) || coupon.getStartDate().before(Date.valueOf(LocalDate.now())) || coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
      throw new ServerException(ErrorType.INVALID_DATE);
    }
    if (coupon.getName().isEmpty() || coupon.getName().length() < 2) {
      throw new ServerException(ErrorType.INVALID_NAME);
    }
    if (coupon.getPrice() <= 0) {
      throw new ServerException(ErrorType.INVALID_PRICE);
    }
    if (!categoryLogic.isCategoryExist(coupon.getCategory().getId())) {
      throw new ServerException(ErrorType.CATEGORY_DOES_NOT_EXIST);
    }
    if (companyLogic.getCompany(coupon.getCompany().getId()) == null) {
      throw new ServerException(ErrorType.COMPANY_DOES_NOT_EXIST);
    }
  }

  public void updateCoupon(Coupon coupon) throws ServerException {
    CouponValidation(coupon);
    couponDal.save(coupon);
  }

  public void removeCoupon(long couponId) throws ServerException {
    couponDal.deleteById(couponId);
  }

  public CouponDto getCoupon(long couponId) throws ServerException {
    return couponDal.findById(couponId);

  }

  public List<CouponDto> getCouponsByCompanyID(long companyId, int page) throws ServerException {
    Pageable pageable = PageRequest.of(page - 1, Consts.LIMITPERPAGE);
    return couponDal.getCouponsByCompanyID(companyId, pageable);
  }

  public List<CouponDto> getCouponsByCategoryID(long categoryId, int page) throws ServerException {
    Pageable pageable = PageRequest.of(page - 1, Consts.LIMITPERPAGE);
    return couponDal.getCouponsByCategoryID(categoryId, pageable);
  }

  public List<CouponDto> getAllCoupons(int page) throws ServerException {
    Pageable pageable = PageRequest.of(page - 1, Consts.LIMITPERPAGE);
    return couponDal.getAllCoupons(pageable);
  }

  public List<CouponDto> getAllCouponsOrderByPrice() throws ServerException {
//        return couponDal.getAllCouponsOrderByPrice();
    return null;
  }


  public CouponDto getMinPriceCouponsByCategoryID(long categoryId) throws ServerException {
//        return couponDal.getMinPriceCouponsByCategoryID(categoryId);
    return null;
  }
}
