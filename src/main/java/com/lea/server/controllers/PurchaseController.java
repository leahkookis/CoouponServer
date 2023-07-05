package com.lea.server.controllers;

import com.lea.server.beans.PurchaseDto;
import com.lea.server.entity.Purchase;
import com.lea.server.logic.PurchaseLogic;
import com.lea.server.utils.JWTUtils;
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
  public long createPurchase(@RequestHeader String authorization  ,@RequestBody Purchase purchase) throws Exception {
    JWTUtils.validateToken(authorization);
    return purchaseLogic.createPurchase(purchase);
  }

  @PutMapping
  public void updatePurchase(@RequestHeader String authorization, @RequestBody Purchase purchase) throws Exception {
    JWTUtils.validateToken(authorization);
    purchaseLogic.updatePurchase(purchase);
  }

   @DeleteMapping("/{purchaseId}")
  public void removePurchase(@RequestHeader String authorization, @PathVariable("purchaseId") long purchaseId) throws Exception {
     JWTUtils.validateToken(authorization);
    purchaseLogic.removePurchase(purchaseId);
  }

  @GetMapping("/{purchaseId}")
  public PurchaseDto getPurchaseByPurchaseID(@RequestHeader String authorization, @PathVariable("purchaseId") int purchaseId) throws Exception {
    JWTUtils.validateToken(authorization);
    return purchaseLogic.getPurchase(purchaseId);
  }

  @GetMapping("/bycustomer")
  public List<PurchaseDto> getPurchasesByUserID(@RequestHeader String authorization, @RequestParam("customerid") long customerId) throws Exception {
    JWTUtils.validateToken(authorization);
    return purchaseLogic.getPurchasesByCustomerID(customerId, 1);
  }

  @GetMapping
  public List<PurchaseDto> getAllPurchases(@RequestHeader String authorization, @RequestParam("page") int page) throws Exception {
    JWTUtils.validateToken(authorization);
    return purchaseLogic.getAllPurchases(page);
  }

  @GetMapping("/bycompany")
  public List<PurchaseDto> getPurchaseByCompanyID(@RequestHeader String authorization, @RequestParam("companyId") int companyId, @RequestParam("page") int page) throws Exception {
    JWTUtils.validateToken(authorization);
    return purchaseLogic.getPurchasesByCompanyID(companyId, page);
  }

}
