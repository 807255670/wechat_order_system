package com.hodor.demo.service;

import com.hodor.demo.dataobject.SellerInfo;

/**
 * Created By Fan Huiliang
 * 2019-07-20 19:52
 */
public interface SellerService {

    /**
     * 通过获取的userid获得卖家信息
     * @param userid
     * @return
     */
    SellerInfo findSellerInfoByUserid(String userid);

}
