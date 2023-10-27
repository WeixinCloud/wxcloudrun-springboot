package com.tencent.wxcloudrun.http;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class MyHttpConverter extends MappingJackson2HttpMessageConverter {
    public MyHttpConverter() {
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_PLAIN);
        mediaTypeList.add(MediaType.TEXT_HTML);
        setSupportedMediaTypes(mediaTypeList);
    }
}
