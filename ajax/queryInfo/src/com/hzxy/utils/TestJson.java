package com.hzxy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzxy.bean.Province;

public class TestJson {
    public static void main(String[] args) throws JsonProcessingException {
        //使用jackson吧java对象转为json格式的字符串

        Province province = new Province();
        province.setId(1);
        province.setName("河北");
        province.setJiancheng("冀");
        province.setShenghui("石家庄");

        //使用jackson 吧province转换为json
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(province);
        System.out.println(json);

    }
}
