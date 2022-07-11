package com.hezexy.jdbc;

import java.sql.*;

//使用PreparedStatement完成增删改
public class JDBCTest04 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","root");
            //获取数据库操作对象
            /*//增
            String sql = "insert into dept(deptno,dname,loc) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //给问号传值
            ps.setInt(1,50);
            ps.setString(2,"销售部");
            ps.setString(3,"北京");*/
           /* //删
            String sql = "delete from dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            //给问号传值
            ps.setInt(1,50);*/
            //改
            String sql = "update dept set dname = ?,loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            //给占位符传值
            ps.setString(1,"软件部");
            ps.setString(2,"菏泽");
            ps.setInt(3,40);

            //执行sql语句
            int count = ps.executeUpdate();
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }
}
