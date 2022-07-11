package com.hzxy.dao;

import com.hzxy.bean.Province;

import java.sql.*;

//使用jdbc访问数据库
public class ProvinceDao {

    //根据id获取名称
    public String queryProviceNameById(Integer proviceId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        String url = "jdbc:mysql://localhost:3306/springdb";
        String name = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","root");
            sql = "select name from province where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,proviceId);
            rs = ps.executeQuery();
           if (rs.next()){
                name = rs.getString("name");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return name;

    }
    //根据id获取一个完整的Province对象
    public Province queryProviceById(Integer proviceId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        String url = "jdbc:mysql://localhost:3306/springdb";
        Province province = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,"root","root");
            sql = "select id, name, jiancheng, shenghui from province where id = ? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,proviceId);
            rs = ps.executeQuery();
            if (rs.next()){
                province = new Province();
                province.setId(rs.getInt("id"));
                province.setName(rs.getString("name"));
                province.setJiancheng(rs.getString("jiancheng"));
                province.setShenghui(rs.getString("shenghui"));

            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if (rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (ps!=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return province;

    }
}
