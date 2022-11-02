package com.tencent.wxcloudrun.dao.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tencent.wxcloudrun.dao.entity.AdsOrderEntity;
import com.tencent.wxcloudrun.dao.mapper.AdsOrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<AdsOrderEntity> queryByOpenIdList(List<String> openidList) {
        LambdaQueryWrapper<AdsOrderEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(AdsOrderEntity::getOpenid, openidList);
        return this.list(wrapper);
    }
}
