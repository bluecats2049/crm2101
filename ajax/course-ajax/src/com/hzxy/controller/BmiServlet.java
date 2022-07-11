package com.hzxy.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet("/bmiServlet")
public class BmiServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取参数
        String name = request.getParameter("name");
        String height = request.getParameter("h");
        String weight = request.getParameter("w");

        //计算bmi ： bmi=体重/身高的平方

        float h = Float.valueOf(height);
        float w = Float.valueOf(weight);

        float bmi = w / (h * h);
        //判断bmi的范围
        String msg = "";
        if (bmi <= 18.5){
            msg = "您比较瘦";
        }else if (bmi > 18.5 && bmi<=23.9){
            msg = "您身材真棒，标准的正常的";
        }else if (bmi > 24 && bmi <= 27){
            msg = "您有点胖";
        }else {
            msg = "您有很胖了，该减肥了";
        }
        System.out.println("msg"+msg);
        msg = "您好"+name+"先生/女生，您的bmi值是："+bmi+","+msg;

        //把数据存入到request域中
        request.setAttribute("msg",msg);

        //转发到新的界面
        request.getRequestDispatcher("/result.jsp").forward(request,response);



    }
}
