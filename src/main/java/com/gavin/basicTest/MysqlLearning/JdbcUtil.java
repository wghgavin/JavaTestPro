package com.gavin.basicTest.MysqlLearning;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 德鲁伊数据库连接池
 */
public class JdbcUtil {
    private  static DataSource ds;
    private  static ThreadLocal<Connection> local;
    static {
        try {
            Properties pro = new Properties();
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds= DruidDataSourceFactory.createDataSource(pro);
            local=new ThreadLocal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static DataSource getDs(){
        return ds;
    }


    /**
     * 这个方法不能保证同一个线程共享同一个连接对象,
     * 因为每次get不一定是同一个对象,使用ThreadLocal来共享一个变量
     * @return
     */
//    public static Connection getConnection(){
//        try {
//            return ds.getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    /**
     * 获取连接
     * @return
     */
    public static Connection getConnection() {
        try {
            Connection conn = local.get();//如果能拿到，说明已经拿过
            if(conn==null){//没有拿
                conn = ds.getConnection();
                local.set(conn);
            }
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭连接
     */
    public  static void free(){
        try {
            Connection conn =local.get();
            if(conn!=null){
                local.remove();
                conn.setAutoCommit(true);//还原连接为自动提交，别人再拿到这个连接，默认自动提交
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
