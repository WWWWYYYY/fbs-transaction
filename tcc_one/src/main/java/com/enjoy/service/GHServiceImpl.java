package com.enjoy.service;

import org.mengyun.tcctransaction.api.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ghService")
public class GHServiceImpl implements GHService{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NHService nhService;

    @Compensable(confirmMethod = "confirmOrder", cancelMethod = "cancelOrder")
    @Transactional
    public int doOrder(String busId,String idcard) {//try方法

        System.out.println("frozen order："+busId);

        //幂等性要求，如果已经执行过，则直接返回
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(1) FROM tcc_fly_order t WHERE t.bus_id = ?",Integer.class,busId);
        if (count > 0){
            return 1;
        }

        //锁定订单
        int ret = jdbcTemplate.update(
                "UPDATE tcc_fly_order SET frozen = 1,bus_id=? WHERE STATUS = 0 AND frozen=0 LIMIT 1",busId);
        if (ret != 1){
            throw new RuntimeException("下单失败，国航无票可订");
        }
        nhService.doOrder(busId,idcard);
        return ret;
    }

    public int confirmOrder(String busId,String idcard) {
        System.out.println("confirm order："+busId);

        //本语句是幂等性的
        int ret = jdbcTemplate.update("UPDATE tcc_fly_order SET frozen = 0,STATUS = 1,idcard=? WHERE bus_id=? and STATUS = 0 ",idcard,busId);
        return ret;
    }

    public int cancelOrder(String busId,String idcard) {//补偿性
        System.out.println("cancel order："+busId);

        //本语句是幂等性的
        int ret = jdbcTemplate.update("UPDATE tcc_fly_order SET frozen = 0,STATUS = 0 WHERE bus_id=?",busId);
        return ret;
    }
}