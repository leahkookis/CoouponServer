package com.lea.server.controllers;

import com.lea.server.beans.PurchaseDto;
import com.lea.server.entity.Purchase;
import com.lea.server.logic.PurchaseLogic;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/purchase")

public class PurchaseController {
  private PurchaseLogic purchaseLogic;

  @Autowired
  public PurchaseController(PurchaseLogic purchaseLogic) {
    this.purchaseLogic = purchaseLogic;
  }

  @PostMapping
  public long createPurchase(@RequestBody Purchase purchase) throws ServerException {
    return purchaseLogic.createPurchase(purchase);
  }

  @PutMapping
  public void updatePurchase(@RequestBody Purchase purchase) throws ServerException {
    purchaseLogic.updatePurchase(purchase);
  }

  @PostMapping("/buycoupons")
  public void updateCouponsToBuy(@RequestBody Integer[] purchaseIds) throws ServerException {
    purchaseLogic.updateCouponsToBuy(purchaseIds);
  }

  @DeleteMapping("/{purchaseId}")
  public void removePurchase(@PathVariable("purchaseId") long purchaseId) throws ServerException {
    purchaseLogic.removePurchase(purchaseId);
  }

  @GetMapping("/{purchaseId}")
  public PurchaseDto getPurchaseByPurchaseID(@PathVariable("purchaseId") int purchaseId) throws ServerException {
    return purchaseLogic.getPurchase(purchaseId);
  }

  @GetMapping("/bycustomer")
  public List<PurchaseDto> getPurchasesByUserID(@RequestParam("customerid") long customerId) throws ServerException {
    return purchaseLogic.getPurchasesByCustomerID(customerId, 1);
  }

  @GetMapping
  public List<PurchaseDto> getAllPurchases(@RequestParam("page") int page) throws ServerException {
    return purchaseLogic.getAllPurchases(page);
  }

  @GetMapping("/{companyId}")
  public List<PurchaseDto> getPurchaseByCompanyID(@RequestParam("companyId") int companyId, @RequestParam("page") int page) throws ServerException {
    return purchaseLogic.getPurchasesByCompanyID(companyId, page);
  }

}
