package com.hezexy.jdbc;

import java.sql.*;

/*
JDBC默认情况下对事物是怎么处理的？
   模拟银行转账操作，A账户向B账户转账10000元
   A账户减少10000，B账户增加10000
   必须同时成功，或同时失败

   转账需要执行两条update语句的

   JDBC默认情况下支持自动提交：
       什么叫做自动提交的？
          只需要执行一条DML语句就自动提交一次
          实际开发中必须将JDBC的自动提交机制关闭掉，改成手动提交
          conn.setAutoCommit(false);关闭自动提交
          conn.commit();手动提交
          conn.rollback();手动回滚


 */
public class JDBCTest06 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","root");
            //开启事务：关闭自动提交机制
            conn.setAutoCommit(false);
            //获取数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);
            //给问号传值
            ps.setDouble(1,10000);
            ps.setString(2,"A");
            int count = ps.executeUpdate();//更新成功以后表示更新一条，返回1

            //Thread.sleep(1000*20);
            //模拟异常
            //String s = null;
            //s.toString();

            //给问号传值
            ps.setDouble(1,10000);
            ps.setString(2,"B");
            //执行SQL语句
            count += ps.executeUpdate();//再次更新一条再返回1

            System.out.println(count==2 ? "转账成功":"转账失败");

            //代码能执行到此处，说明上面的代码没有出现任何异常，表示都成功了，手动提交
            //手动提交，事务结束
            conn.commit();
        } catch (Exception e) {
            //出现异常，为了保险起见，这里要回滚
            try {
                if (conn != null){
                    conn.rollback();
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
