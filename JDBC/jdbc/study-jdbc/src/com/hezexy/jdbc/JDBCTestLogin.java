package com.hezexy.jdbc;

import java.sql.*;
import java.util.HashMap;

import java.util.Map;
import java.util.Scanner;

//模拟用户登陆
public class JDBCTestLogin {
    public static void main(String[] args) {

        //初始化一个界面，可以让用户输入用户名密码
        Map<String,String> userLoginInfo = initUI();
        //连接数据库验证用户名和密码是否正确
        boolean ok = checkNameAndPwd(userLoginInfo.get("loginName"),userLoginInfo.get("loginPwd"));
        System.out.println(ok ? "登陆成功":"登陆失败");

    }

    /**
     * 验证用户名和密码
     * @param loginName 登录名
     * @param loginPwd 登陆密码
     * @return true表示登陆成功，false表示登陆失败
     */
    private static boolean checkNameAndPwd(String loginName, String loginPwd) {
        boolean ok = false;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","root");
            //获取数据库操作对像
            //一个问号？是一个占位符,一个占位符只能接收一个值或者数据
            String sql = "select * from t_user where login_name = ? and login_pwd = ?";
            stmt = conn.prepareStatement(sql);//此时会发送sql给DBMS,进行sql语句的编译
            //需要给占位符？传值
            //jdbc中所有的下标都是从1开始的
            stmt.setString(1,loginName);
            stmt.setString(2,loginPwd);
            //执行sql
            rs = stmt.executeQuery();
            //处理结果集
            if (rs.next()){
                ok = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
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

        return ok;
    }

    /**
     * 初始化界面，并且接受用户的输入
     * @return
     */
    private static Map<String,String> initUI() {
        System.out.println("欢迎使用该系统，请输入用户名和密码进行身份验证：");
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = sc.next();
        System.out.println("密码：");
        String loginPwd = sc.next();

        //将用户名和密码放到map集合当中
        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);
        //返回Map
        return userLoginInfo;
    }
}
