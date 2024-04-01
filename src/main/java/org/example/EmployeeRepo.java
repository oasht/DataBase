package org.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface EmployeeRepo extends JpaRepositoryImplementation<Employee, Integer> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department")
    List<Employee> findAll();

}
