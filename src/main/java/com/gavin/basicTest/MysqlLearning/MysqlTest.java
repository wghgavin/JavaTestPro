package com.gavin.basicTest.MysqlLearning;

import com.gavin.basicTest.MysqlLearning.bean.Department;
import com.gavin.basicTest.MysqlLearning.dao.IDepartmentDAO;
import com.gavin.basicTest.MysqlLearning.dao.impl.DepartmentDAOImpl;

import java.sql.*;

/**
 * jdbc连接字符串:"jdbc:mysql://localhost:3306/test"
 * Connection接口:传表连接
 * DriverManager:驱动管理类
 */
public class MysqlTest {
    public static void main(String[] args) {
        IDepartmentDAO dao = new DepartmentDAOImpl();
        int result = dao.add(new Department(1,"测试","测试案例"));
        System.out.println(result>0?"成功添加":"添加失败");
    }

    /**
     * 更改操作
     */
    public void UpdateTest(){
        try {
            Class<?> mysql = Class.forName("com.mysql.cj.jdbc.Driver");
            //必须要加时区不然报错
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pwd = "123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            String sql = "insert into dept(name,description,tel) values('测试','ss','123456')";
            //创建Statement对象
            Statement statement=conn.createStatement();
            int result= statement.executeUpdate(sql);//凡是insert,update,delete都是更新数据库
            System.out.println(result);
            statement.close();
            conn.close();
            //select都是查询数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询操作
     */
    public void GetTest(){
        try {
            Class<?> mysql = Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pwd = "123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            String sql ="select * from dept";
            Statement statement=conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
             int id=   result.getInt("id");
             String name = result.getString("name");
                System.out.println(id+"\t"+name);
            }
            result.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sql拼接解决,返回自增值
     */
    public void PreparedStatementTest(){
        try {
            Class<?> mysql = Class.forName("com.mysql.cj.jdbc.Driver");
            //必须要加时区不然报错
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pwd = "123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            String sql = "insert into dept(name,description,tel) values(?,?,?)";
            //创建PreparedStatement对象
            PreparedStatement statement=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //把?具体值传进去
            statement.setObject(1,"s");
            statement.setObject(2,"123");
            statement.setObject(3,"456789123");
            int result= statement.executeUpdate();//凡是insert,update,delete都是更新数据库
            System.out.println(result);
            //获取自增长键值
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){//因为自增长的键值只有一个单值
                System.out.println("编号"+rs.getObject(1));
            }
            rs.close();
            statement.close();
            conn.close();
            //select都是查询数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批处理使用addBatch
     */
    public void InsertManyTest(){
        try {
            Class<?> mysql = Class.forName("com.mysql.cj.jdbc.Driver");
            //必须要加时区不然报错
            String url = "jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pwd = "123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            String sql = "insert into dept(name,description,tel) values(?,?,?)";
            //创建PreparedStatement对象
            PreparedStatement pst=conn.prepareStatement(sql);
            for(int i=0;i<100;i++){
                pst.setObject(1,"测试数据"+i);
                pst.setObject(2,"测试描述"+i);
                pst.setObject(3,"电话"+i);
                pst.addBatch();//先添加到批处理命令组缓存
            }
            //返回了100个结果
            int[] result = pst.executeBatch();
            System.out.println(result.length);
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务测试
     */
    public void TransmissionTest(){
        try {
            Class<?> mysql = Class.forName("com.mysql.cj.jdbc.Driver");
            //必须要加时区不然报错
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            String user = "root";
            String pwd = "123";
            Connection conn = DriverManager.getConnection(url, user, pwd);
            //设置手动提交事务
            conn.setAutoCommit(false);
            String sql = "update dept set name=? where id =?";
            //创建Statement对象
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setObject(1,"sadaszf");
            pst.setObject(2,2);
            int result= pst.executeUpdate();//凡是insert,update,delete都是更新数据库
            if(result>0){
                System.out.println("更新成功事务提交");
                conn.commit();
            }else {
                System.out.println("更新失败事务回滚");
                conn.rollback();
            }
            pst.close();
            conn.close();
            //select都是查询数据库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解决每次请求数据都要新建连接
     * 1.吧注册驱动封装到一个工具类
     * 2.使用数据库连接池
     */


    /**
     * 数据库连接池测试
     */
    public void mysqlThreadPoolTest(){
        try {
            Connection conn =  JdbcUtil.getConnection();
            String sql = "insert into dept(name,description,tel) values(?,?,?)";
            //创建PreparedStatement对象
            PreparedStatement pst=conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //把?具体值传进去
            pst.setObject(1,"s");
            pst.setObject(2,"123");
            pst.setObject(3,"456789123");
            int result= pst.executeUpdate();
            System.out.println(result);
            pst.close();
            JdbcUtil.free();//关闭当前线程的数据库连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
