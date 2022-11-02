package com.tencent.wxcloudrun.common.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author tangsh
 * @date 2022/10/27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCodeParam {

    @NotNull(message = "code不能为空")
    private String code;
}
