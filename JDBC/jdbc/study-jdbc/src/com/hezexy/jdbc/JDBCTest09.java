package com.hezexy.jdbc;

import com.hezexy.jdbc.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest09 {
    public static void main(String[] args) {
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "update emp set sal = sal * 10 where job = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            int count = ps.executeUpdate();
            System.out.println("更新了"+count+"条");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
