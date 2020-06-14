package com.gavin.basicLearning.MysqlLearning.dao;

import com.gavin.basicLearning.MysqlLearning.bean.Department;

import java.util.ArrayList;

public interface IDepartmentDAO {
    int add(Department department);
    ArrayList<Department> getAll();
    int update(Department department);
    int deleteById(int id);
}
