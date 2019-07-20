package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Fan Huiliang
 * 2019-07-20 17:44
 */
public interface SellerInfoDao extends JpaRepository<SellerInfo,String> {

    SellerInfo findByUserid(String userid);



}
