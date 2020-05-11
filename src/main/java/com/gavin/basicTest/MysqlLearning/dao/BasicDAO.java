package com.gavin.basicTest.MysqlLearning.dao;

import com.gavin.basicTest.MysqlLearning.JdbcUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * 数据访问的基层接口
 */
@SuppressWarnings("all")
public class BasicDAO {
    public int update(String sql, Object... args) {
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pst.setObject(i + 1, args[i]);
                }
            }
            int result = pst.executeUpdate();
            pst.close();
            JdbcUtil.free();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * @param clazz代表bean的class
     * @param sql 代表sql语句
     * @param args sql语句中的参数
     * @param <T>代表javabean对象
     * @return
     */
    public <T> ArrayList<T> getAll(Class<T> clazz, String sql, Object... args) {
        try {
            Connection conn = JdbcUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    pst.setObject(i + 1, args[i]);
                }
            }
            ResultSet rs = pst.executeQuery();
            ArrayList<T> list = new ArrayList<>();
            //获取结果集的元数据对象
            ResultSetMetaData metaData = pst.getMetaData();
            int count = metaData.getColumnCount();//获取列数
            while (rs.next()) {//循环一次就是一行
                T instance = clazz.getDeclaredConstructor().newInstance();
                for (int i = 0; i < count; i++) {
                    //有几列就有几个属性
                    //getDeclaredField是可以获取一个类的所有字段.
                    //getField只能获取类的public 字段.
                    Field f = clazz.getDeclaredField(metaData.getColumnName(i+1));//mysql序号从1开始
                    f.setAccessible(true);
                    f.set(instance,rs.getObject(i+1));//mysql序号从1开始
                }
                list.add(instance);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
