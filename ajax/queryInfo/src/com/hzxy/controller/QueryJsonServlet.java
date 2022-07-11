package com.hzxy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzxy.bean.Province;
import com.hzxy.dao.ProvinceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/queryjson")
public class QueryJsonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //默认值
        String json = "{}";
        //获取请求参数，省份的id
        String proid = request.getParameter("proid");

        //判断proid有值时，调用dao查询数据
        if (proid != null && proid.trim().length() > 0){
            ProvinceDao dao = new ProvinceDao();
            Province p = dao.queryProviceById(Integer.valueOf(proid));

            //需要使用jackson吧Province对象转换为json
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(p);
        }

        //把获取的数据通过网络传给ajax中的异步对象，响应结果数据
        //指定服务器端返回给浏览器的时json格式的数据
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(json);//输出数据----会赋给ajax中 responseText属性
    }
}
