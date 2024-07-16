package com.attraya.repository;

import com.attraya.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository{

    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO EMPLOYEE_DATA (name, dept, email, doj) VALUES (?, ?, ?, ?)",
                employee.getName(), employee.getDept(), employee.getEmail(), employee.getDoj());
    }

      /* Approach 1: Using separate class that implements RowMapper */
//    @Override
//    public List<Employee> findAll() {
//        return jdbcTemplate.query("SELECT * FROM EMPLOYEE_DATA", new EmployeeRowMapper());
//    }

      /* Approach 2: Using Anonymous implementation */
//    @Override
//    public List<Employee> findAll() {
//        return jdbcTemplate.query("SELECT * FROM EMPLOYEE_DATA", new RowMapper<Employee>() {
//            @Override
//            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//                 return Employee.builder()
//                        .id(rs.getInt("id"))
//                        .name(rs.getString("name"))
//                        .dept(rs.getString("dept"))
//                        .email(rs.getString("email"))
//                        .doj(rs.getDate("doj"))
//                        .build();
//            }
//        });
//    }

    /* Approach 3: Using Lambda Expression */
    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE_DATA",
                (rs, rowNum) ->
                        Employee.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .dept(rs.getString("dept"))
                                .email(rs.getString("email"))
                                .doj(rs.getDate("doj"))
                                .build()

        );
    }

    /* Approach 4: Maps automatically(preferable) */
    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE_DATA", new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE_DATA WHERE id = ?", new BeanPropertyRowMapper<>(Employee.class), id);
    }

    @Override
    public String getNameById(int id) {
        return jdbcTemplate.queryForObject("SELECT name FROM EMPLOYEE_DATA WHERE id = ?", String.class, id);
    }

    @Override
    public List<Employee> findByNameAndDept(String name, String dept) {
        return jdbcTemplate.query("SELECT * FROM EMPLOYEE_DATA WHERE name = ? AND DEPT = ?", new EmployeeRowMapper(), name, dept);
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE EMPLOYEE_DATA SET name = ?, dept = ?, email = ?, doj = ? WHERE id = ?",
                employee.getName(), employee.getDept(), employee.getEmail(), employee.getDoj(), employee.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM EMPLOYEE_DATA WHERE id = ?", id);
    }
}
