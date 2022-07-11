package com.hezexy.jdbc;

import com.hezexy.jdbc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
08和09共同演示
使用for update的时候，最好是锁主键值，或者具有unique约束的字段
锁别的字段可能会导致整个表锁住。可以查不能改
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //注册驱动
            conn = DBUtil.getConnection();
            //开启事物
            conn.setAutoCommit(false);
            //执行
            String sql = "select ename,sal from emp where job= ? for update";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("ename")+","+rs.getString("sal"));
            }

            //睡眠
            Thread.sleep(1000*20);
            //提交事物
            conn.commit();
        } catch (SQLException throwables) {
            if (conn != null){
                try {
                    //回滚事物
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }
}
