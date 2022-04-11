package com.endava.jpademo.service;

import com.endava.jpademo.dot.EmployeeLoginDto;
import com.endava.jpademo.entity.Employee;
import com.endava.jpademo.exception.EmployeeNotFoundException;
import com.endava.jpademo.exception.NoDataFoundException;
import com.endava.jpademo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository repository;


    @Autowired
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = repository.findAll();
        if (list.isEmpty()) {
            throw new NoDataFoundException();
        }
        return list;
    }

    @Override
    public Employee getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public List<Employee> getByFirstName(String firstName) {
        List<Employee> list = repository.getEmployeesByFirstNameIgnoreCase(firstName);
        if (list.isEmpty()) {
            throw new NoDataFoundException();
        }
        return list;
    }

    @Override
    public List<Employee> getByLastName(String lastName) {
        List<Employee> list = repository.findAllByLastNameIgnoreCase(lastName);
        if (list.isEmpty()) {
            throw new NoDataFoundException();
        }
        return list;
    }

    @Override
    @Transactional
    public Employee addOne(Employee employee) {
        return repository.save(employee);
    }

    @Override
    @Transactional
    public List<Employee> addList(List<Employee> employees) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employees) {
            repository.save(employee);
            list.add(employee);
        }
        return list;
    }

    @Override
    @Transactional
    public Employee update(Employee newEmployee, Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setEmail(newEmployee.getEmail());
                    employee.setPassword(newEmployee.getPassword());
                    return repository.save(employee);
                }).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Override
    public String deleteById(Long id) {
        this.validateEmployee(id);
        repository.deleteById(id);
        return "Employee with ID: " + id + " deleted successfully!";

    }

    @Override
    public String deleteAll() {
        if (repository.findFirst() == null) {
            throw new NoDataFoundException();
        }
        repository.deleteAll();
        return "Employees were deleted.";
    }

    public boolean login(EmployeeLoginDto employeeLoginDto) {

        Employee employee = repository.findEmployeeByEmail(employeeLoginDto.getEmail())
                .orElseThrow(() -> new EmployeeNotFoundException(employeeLoginDto.getEmail()));

        return employee.getPassword().equals(employeeLoginDto.getPassword());
    }

    private void validateEmployee(Long id) {
        this.repository.findById(id).orElseThrow(
                () -> new EmployeeNotFoundException(id));
    }
}
