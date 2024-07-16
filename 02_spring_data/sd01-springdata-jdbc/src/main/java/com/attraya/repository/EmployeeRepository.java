package com.attraya.repository;

import com.attraya.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    int save(Employee employee);

    List<Employee> findAll();

    List<Employee> getAllEmployees();

    Employee findById(int id);

    String getNameById(int id);

    List<Employee> findByNameAndDept(String name, String dept);

    int update(Employee employee);

    int delete(int id);

}
