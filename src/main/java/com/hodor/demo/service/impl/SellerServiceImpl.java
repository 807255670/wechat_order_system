package com.hodor.demo.service.impl;

import com.hodor.demo.Dao.SellerInfoDao;
import com.hodor.demo.dataobject.SellerInfo;
import com.hodor.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By Fan Huiliang
 * 2019-07-20 19:53
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerInfoDao sellerInfoDao;
    @Override
    public SellerInfo findSellerInfoByUserid(String userid) {
        return sellerInfoDao.findByUserid(userid);
    }
}
