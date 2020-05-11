package com.gavin.basicTest.MysqlLearning.dao;

import com.gavin.basicTest.MysqlLearning.bean.Department;

import java.util.ArrayList;

public interface IDepartmentDAO {
    int add(Department department);
    ArrayList<Department> getAll();
    int update(Department department);
    int deleteById(int id);
}
