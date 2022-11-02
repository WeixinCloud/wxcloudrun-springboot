package com.tencent.wxcloudrun.admin.server;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tencent.wxcloudrun.common.constants.CategoryEnum;
import com.tencent.wxcloudrun.common.dto.PageDTO;
import com.tencent.wxcloudrun.dao.entity.AdsInfoEntity;
import com.tencent.wxcloudrun.dao.repository.AdsInfoRepository;
import com.tencent.wxcloudrun.dao.repository.AdsOrderRepository;
import com.tencent.wxcloudrun.common.request.AdsBaseParam;
import com.tencent.wxcloudrun.common.request.AdsCreateParam;
import com.tencent.wxcloudrun.common.request.AdsEditParam;
import com.tencent.wxcloudrun.common.request.AdsPageParam;
import com.tencent.wxcloudrun.common.utils.PageUtils;
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
public class AdminAdsInfoServer {

    @Autowired
    private AdsInfoRepository adsInfoRepository;
    @Autowired
    private AdsOrderRepository adsOrderRepository;

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

    public AdsInfoEntity detail(AdsBaseParam param) {
        return adsInfoRepository.getById(param.getId());
    }


    public void create(AdsCreateParam param) {
        param.checkParam();
        AdsInfoEntity entity = new AdsInfoEntity();
        BeanUtils.copyProperties(param, entity);
        adsInfoRepository.save(entity);

    }

    public void edit(AdsEditParam param) {
        param.checkParam();
        AdsInfoEntity update = new AdsInfoEntity();
        BeanUtils.copyProperties(param, update);
        adsInfoRepository.updateById(update);
    }

    public void on(AdsBaseParam param) {
        AdsInfoEntity update = new AdsInfoEntity();
        update.setId(param.getId());
        update.setStatus("ON");
        adsInfoRepository.updateById(update);
    }


    public void off(AdsBaseParam param) {
        AdsInfoEntity update = new AdsInfoEntity();
        update.setId(param.getId());
        update.setStatus("OFF");
        adsInfoRepository.updateById(update);
    }
}
