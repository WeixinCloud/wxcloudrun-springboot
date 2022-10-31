package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.mapper.AdsOrderMapper;
import org.springframework.stereotype.Repository;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Repository
public class AdsOrderRepository extends BaseRepository<AdsOrderMapper, AdsOrderEntity> {

    public AdsOrderEntity getOneByOrderNo(String outTradeNo) {
        LambdaQueryWrapper<AdsOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdsOrderEntity::getOutTradeNo, outTradeNo);
        return this.getOne(wrapper);
    }
}
