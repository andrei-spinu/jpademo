package com.endava.jpademo.service;

import com.endava.jpademo.entity.Employee;
import com.endava.jpademo.dot.EmployeeLoginDto;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee getById(Long id);
    List<Employee> getByFirstName(String firstName);
    List<Employee> getByLastName(String lastName);
    Employee addOne(Employee employee);
    List<Employee> addList(List<Employee> employees);
    Employee update(Employee newEmployee, Long id);
    String deleteById(Long id);
    String deleteAll();
    boolean login(EmployeeLoginDto employeeLoginDto);

}
