package com.project.server.logic;

import com.project.server.enums.Type;
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
    if(coupon.getCategory().equals("Food")){
    coupon.setUrl("https://heninthekitchen.com/wp-content/uploads/2021/04/IMG_1849-1small-683x1024.jpg");}
    if(coupon.getCategory().equals("Hotels")){
      coupon.setUrl("https://static.wixstatic.com/media/6db993_054071a59d5642698111b09bcd91877b~mv2_d_6720_4181_s_4_2.jpg/v1/fill/w_2500,h_1555,al_c/6db993_054071a59d5642698111b09bcd91877b~mv2_d_6720_4181_s_4_2.jpg");}
    if(coupon.getCategory().equals("Massage")){
      coupon.setUrl("https://cdn.baligam.co.il/_media/media/74142/481902.jpg");}
    if(coupon.getCategory().equals("Flights")){
      coupon.setUrl("https://www.familytour.co.il/wp-content/uploads/2017/07/%D7%98%D7%99%D7%A1%D7%95%D7%AA-960x500.jpg");}
    if(coupon.getCategory().equals("Movies")){
      coupon.setUrl("https://www.kolhazman.co.il/wp-content/uploads/2018/12/213412.jpg");}

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
