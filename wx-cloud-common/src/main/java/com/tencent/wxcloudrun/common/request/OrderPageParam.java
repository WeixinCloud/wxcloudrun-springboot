package com.tencent.wxcloudrun.common.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author tangsh
 * @date 2022/10/31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPageParam implements Serializable {

    @NotNull(message = "起始页不能为空")
    private Integer pageNo;

    @NotNull(message = "分页大小不能为空")
    private Integer pageSize;
}
