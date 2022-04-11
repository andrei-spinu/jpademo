package com.endava.jpademo.repository;
import com.endava.jpademo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getEmployeesByFirstNameIgnoreCase(String firstName);
    List<Employee> findAllByLastNameIgnoreCase(String lastName);
    Optional<Employee> findEmployeeByEmail(String email);
    @Query(value = "select * from employees limit 1", nativeQuery = true)
    Employee findFirst();
}
