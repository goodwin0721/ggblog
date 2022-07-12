package com.goodwin.ggblog.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @author goodwin
 */
public class JsonUtils {
    public static String getJson(Object o){
        return getJson(o,"yyyy-MM-dd HH:mm:ss");
    }

    public static String getJson(Object o,String dateFormat){
        ObjectMapper mapper = new ObjectMapper();
        //不使用时间戳的方式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //自定义日期格式对象
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        //指定日期格式
        mapper.setDateFormat(sdf);
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
}
