package com.hezexy.jdbc;


import java.sql.*;

/*
使用PreparedStatement做模糊查询
 */
public class JDBCTest05 {
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
           /* String sql = "select ename from emp where ename like'%?%'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"o");*/

            //重点是占位符该怎么写
            String sql = "select ename from emp where ename like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"%o%");
            //执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename"));
            }

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
