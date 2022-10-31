package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.entity.BaseDO;
import com.tencent.wxcloudrun.mapper.DefaultMapper;

/**
 * @author tangsh
 * @date 2022/10/30
 */
public abstract class BaseRepository<M extends DefaultMapper<T>, T extends BaseDO> extends ServiceImpl<M, T> {
}
