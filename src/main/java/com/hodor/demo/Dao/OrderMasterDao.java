package com.hodor.demo.Dao;

import com.hodor.demo.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By Fan Huiliang
 * 2019-07-08 19:55
 */
public interface OrderMasterDao extends JpaRepository<OrderMaster,String> {


    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
