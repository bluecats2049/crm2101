package com.hezexy.jdbc;

import java.sql.*;
import java.util.Scanner;

/**
 * 需求：用户在控制台上输入desc则降序，输入asc则升序
 * 思考一下：这个应该选择Statement还是选择PreparedStatement呢？
 *      答：Statement因为比较适合字符串拼接。
 *      不能使用PreparedStatement
 *
 */
public class JDBCTest03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入desc或asc【desc表示降序，asc表示降序】");
        //用户输入的
        String orderKey = sc.next();

        //先使用PreparedStatement
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","root");
            //获取预编译的数据库操作对象
            stmt = conn.createStatement();
            //执行sql语句
            String sql = "select ename,sal from emp order by sal " + orderKey;
            rs = stmt.executeQuery(sql);
            //处理查询结果集
            while (rs.next()){
                System.out.println(rs.getString("ename")+","+rs.getString("sal"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {//释放资源
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
