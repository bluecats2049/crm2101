package com.hzxy.controller;

import com.hzxy.dao.ProvinceDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/queryProvice")
public class QueryProviceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("响应了ajax请求");
        String proid = request.getParameter("proid");
        System.out.println(proid);
        String name = "默认是无数据";

        //访问dao查询数据库
        if (proid != null && !"".equals(proid.trim())){
            //创建dao对象
            ProvinceDao dao = new ProvinceDao();
            name = dao.queryProviceNameById(Integer.valueOf(proid));
            System.out.println(name);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(name);
        //out.println("中国");
        out.flush();
        out.close();
    }
}
