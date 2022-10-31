package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.entity.OrderEntity;
import com.tencent.wxcloudrun.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Repository
public class OrderRepository extends BaseRepository<OrderMapper, OrderEntity> {

    public OrderEntity getOneByOrderNo(String outTradeNo) {
        LambdaQueryWrapper<OrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderEntity::getOutTradeNo, outTradeNo);
        return this.getOne(wrapper);
    }
}
