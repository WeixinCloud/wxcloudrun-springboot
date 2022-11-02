package com.tencent.wxcloudrun.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.common.constants.AdsStatusEnum;
import com.tencent.wxcloudrun.common.constants.CategoryEnum;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.common.expection.BizException;
import com.tencent.wxcloudrun.common.expection.ErrorCode;
import com.tencent.wxcloudrun.common.request.AdsBaseParam;
import com.tencent.wxcloudrun.common.request.AdsCreateParam;
import com.tencent.wxcloudrun.common.request.AdsEditParam;
import com.tencent.wxcloudrun.common.request.AdsPageParam;
import com.tencent.wxcloudrun.common.utils.PageUtils;
import com.tencent.wxcloudrun.dao.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.dao.repository.AdsInfoRepository;
import com.tencent.wxcloudrun.web.service.AdminAdsInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author tangsh
 * @date 2022/11/02
 */
@Service
@Slf4j
public class AdminAdsInfoServerImpl implements AdminAdsInfoService {

    @Autowired
    private AdsInfoRepository adsInfoRepository;

    @Override
    public PageDTO<AdsInfoEntity> page(AdsPageParam param) {
        IPage<AdsInfoEntity> page = new Page<>(param.getPageNo(), param.getPageSize());
        LambdaQueryWrapper<AdsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdsInfoEntity::getStatus, "ON");
        if (StringUtils.hasLength(param.getCategory())) {
            CategoryEnum categoryEnum = CategoryEnum.getByCode(param.getCategory());
            queryWrapper.eq(AdsInfoEntity::getCategory, categoryEnum.getCode());
        }
        if (StringUtils.hasLength(param.getTitle())) {
            queryWrapper.like(AdsInfoEntity::getTitle, param.getTitle());
        }
        IPage<AdsInfoEntity> record = adsInfoRepository.page(page, queryWrapper);

        return PageUtils.copy(record);
    }

    @Override
    public AdsInfoEntity detail(AdsBaseParam param) {
        return adsInfoRepository.getById(param.getId());
    }


    @Override
    public void create(AdsCreateParam param) {
        param.checkParam();
        AdsInfoEntity entity = new AdsInfoEntity();
        BeanUtils.copyProperties(param, entity);
        adsInfoRepository.save(entity);

    }

    @Override
    public void edit(AdsEditParam param) {
        param.checkParam();
        AdsInfoEntity entity = checkAdsExist(param.getId());
        if (AdsStatusEnum.ON.name().equals(entity.getStatus())) {
            throw new BizException(ErrorCode.BIZ_BREAK, "上架状态,暂不支持编辑!");
        }
        AdsInfoEntity update = new AdsInfoEntity();
        BeanUtils.copyProperties(param, update);
        adsInfoRepository.updateById(update);
    }

    @Override
    public void on(AdsBaseParam param) {
        AdsInfoEntity entity = checkAdsExist(param.getId());
        if (AdsStatusEnum.ON.name().equals(entity.getStatus())) {
            throw new BizException(ErrorCode.BIZ_BREAK, "已经是上架状态,无需再上架!");
        }
        AdsInfoEntity update = new AdsInfoEntity();
        update.setId(param.getId());
        update.setStatus(AdsStatusEnum.ON.name());
        adsInfoRepository.updateById(update);
    }


    @Override
    public void off(AdsBaseParam param) {
        AdsInfoEntity entity = checkAdsExist(param.getId());
        if (AdsStatusEnum.OFF.name().equals(entity.getStatus())) {
            throw new BizException(ErrorCode.BIZ_BREAK, "已经是下架状态,无需再下架!");
        }
        AdsInfoEntity update = new AdsInfoEntity();
        update.setId(param.getId());
        update.setStatus(AdsStatusEnum.OFF.name());
        adsInfoRepository.updateById(update);
    }

    private AdsInfoEntity checkAdsExist(Integer id) {
        AdsInfoEntity entity = adsInfoRepository.getById(id);
        if (entity == null) {
            log.error("广告不存在 {}", id);
            throw new BizException(ErrorCode.BIZ_BREAK, "广告不存在!");
        }
        return entity;
    }
}
