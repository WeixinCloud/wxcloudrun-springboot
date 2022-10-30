package com.tencent.wxcloudrun.model.dto;

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

    private List<T> records = Collections.emptyList();
    private int total;
    private int size;
    private int pages;
    private int current;

    public static <T extends Serializable> PageDTO<T> emptyList(int pages, int size) {
        PageDTO<T> dto = new PageDTO<>();
        dto.setTotal(0);
        dto.setPages(pages);
        dto.setSize(size);
        return dto;
    }
}
