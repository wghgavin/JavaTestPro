package com.gavin.basicLearning.MysqlLearning.dao;

import com.gavin.basicLearning.MysqlLearning.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class BasicApacheDAO {
    private QueryRunner qr = new QueryRunner(JdbcUtil.getDs());
    public int update(String sql,Object... args){
        try {
            return  qr.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int update(Connection conn,String sql,Object... args){
        try {
            return qr.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }
}
