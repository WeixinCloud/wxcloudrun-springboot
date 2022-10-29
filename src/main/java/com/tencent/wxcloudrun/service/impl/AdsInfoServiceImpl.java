package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.dto.AdsPageParam;
import com.tencent.wxcloudrun.dto.PageDTO;
import com.tencent.wxcloudrun.model.AdsInfoEntity;
import com.tencent.wxcloudrun.repository.AdsInfoRepository;
import com.tencent.wxcloudrun.service.AdsInfoService;
import com.tencent.wxcloudrun.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

@Service
@Slf4j
public class AdsInfoServiceImpl implements AdsInfoService {

    @Autowired
    private AdsInfoRepository adsInfoRepository;

    @Override
    public List<AdsInfoEntity> list() {
        return adsInfoRepository.list();
    }

    @Override
    public PageDTO<AdsInfoEntity> page(AdsPageParam param) {
        int pageNo = param.getPageNo();
        int pageSize = param.getPageSize();
        IPage<AdsInfoEntity> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<AdsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdsInfoEntity::getStatus, "ON");
        IPage<AdsInfoEntity> record = adsInfoRepository.page(page, queryWrapper);
        return PageUtils.copy(record);
    }
}
