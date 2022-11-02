package com.tencent.wxcloudrun.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author tangsh
 * @date 2022/10/27
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> implements Serializable {

    private List<T> records;
    private int total;
    private int size;
    private int pages;
    private int current;
}
