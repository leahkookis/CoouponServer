package com.lea.server.task;


import com.lea.server.dal.ICouponDal;

import java.util.TimerTask;

public class RemoveInvalidCoupons extends TimerTask {
    ICouponDal couponDal;
    @Override
    public void run() {
        try {
//            couponDal.removeInvalidCoupons();
            System.out.println("invalid coupons removed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

