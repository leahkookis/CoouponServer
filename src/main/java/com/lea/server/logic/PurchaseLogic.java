package com.lea.server.logic;



import com.lea.server.beans.PurchaseDto;
import com.lea.server.constanse.Consts;
import com.lea.server.dal.IPurchaseDal;
import com.lea.server.entity.Purchase;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PurchaseLogic {
    private IPurchaseDal purchaseDal;

    @Autowired
    public PurchaseLogic(IPurchaseDal purchaseDal) {
        this.purchaseDal = purchaseDal;
    }

    public long createPurchase(Purchase purchase) throws ServerException {
        purchaseDal.save(purchase);
        return purchase.getId();
    }

    public void updatePurchase(Purchase purchase) throws ServerException {
        purchaseDal.save(purchase);
    }





    public void removePurchase(long purchaseId) throws ServerException {
        purchaseDal.deleteById(purchaseId);
    }

    public PurchaseDto getPurchase(long purchaseID) throws ServerException {
        return purchaseDal.findById(purchaseID);

    }

    public List<PurchaseDto> getAllPurchases(int page) throws ServerException {
        Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
        return purchaseDal.findAll(pageable);

    }

    public List<PurchaseDto> getPurchasesByCustomerID(long customerId, int page) throws ServerException {
        Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
         return purchaseDal.getPurchasesByCustomerID(customerId, pageable);
    }

    public List<PurchaseDto> getPurchasesByCompanyID(long companyId, int page) throws ServerException {
        Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
        return purchaseDal.getPurchasesByCompanyID(companyId, pageable);
    }

}
