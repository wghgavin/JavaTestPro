package com.gavin.basicTest.MysqlLearning.dao.impl;

import com.gavin.basicTest.MysqlLearning.bean.Department;
import com.gavin.basicTest.MysqlLearning.dao.BasicDAO;
import com.gavin.basicTest.MysqlLearning.dao.IDepartmentDAO;

import java.util.ArrayList;

public class DepartmentDAOImpl extends BasicDAO implements IDepartmentDAO {
    @Override
    public int add(Department department) {
        String sql ="insert into department values(NULL,?,?)";
        return update(sql,department.getName(),department.getDescription());
    }

    @Override
    public ArrayList<Department> getAll() {
        String sql ="select * from department";
        ArrayList<Department> list = getAll(Department.class,sql);
        return list;
    }

    @Override
    public int update(Department department) {
        String sql ="update department set name=?,description=? where id=?";
        return update(sql,department.getName(),department.getDescription(),department.getId());
    }

    @Override
    public int deleteById(int id) {
        String sql ="delete from department where id=?";
        return update(sql,id);
    }
}
