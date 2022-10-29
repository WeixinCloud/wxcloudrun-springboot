package com.tencent.wxcloudrun.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.dao.DefaultMapper;
import com.tencent.wxcloudrun.model.BaseDO;

public abstract class BaseRepository<M extends DefaultMapper<T>, T extends BaseDO> extends ServiceImpl<M, T> {


}
