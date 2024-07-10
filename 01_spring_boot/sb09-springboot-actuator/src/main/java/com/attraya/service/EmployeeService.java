package com.attraya.service;

import com.attraya.dto.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class EmployeeService {

    public List<Employee> getEmployees() throws JsonProcessingException {
        List<Employee> employees = IntStream.rangeClosed(1, 10).mapToObj(i -> new Employee(i, "Employee-" + i))
                                        .toList();
        log.info("EmployeeService.getEmployees() :: find all employees from system {}", new ObjectMapper().writeValueAsString(employees));
        return employees;
    }
}
