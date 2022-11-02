
package com.tencent.wxcloudrun.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.dto.PageDTO;

/**
 * @author tangsh
 * @date 2022/10/30
 */
public class PageUtils {

    public static <T> PageDTO<T> copy(IPage<T> page) {
        PageDTO<T> pageDTO = new PageDTO<>();
        pageDTO.setPages(Long.valueOf(page.getPages()).intValue());
        pageDTO.setTotal(Long.valueOf(page.getTotal()).intValue());
        pageDTO.setCurrent(Long.valueOf(page.getCurrent()).intValue());
        pageDTO.setSize(Long.valueOf(page.getSize()).intValue());
        pageDTO.setRecords(page.getRecords());
        return pageDTO;
    }
}
