package org.example;

import com.thedeanda.lorem.LoremIpsum;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DBTest {
    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeRepo employeeRepo;
    
    static PostgreSQLContainer<?> postgres= new PostgreSQLContainer<>(
            "postgres:15-alpine");

   @BeforeAll
    static void beforeAll(){
        postgres.start();
    }

    @AfterAll
    static void afterAll(){
        postgres.stop();
    }

    @BeforeEach
    void populateDb(){
        List<Department> deps = new ArrayList<>();
        for(int i=0;i<5;i++){
            Department department = new Department();
            department.setName(LoremIpsum.getInstance().getCountry());
            deps.add(department);
            departmentRepo.save(department);
        }

        List<Employee> employees = new ArrayList<>();
        for(int i=0;i<5;i++){
            Employee employee = new Employee();
            employee.setName(LoremIpsum.getInstance().getFirstName());
            employee.setDepartment(deps.get(i%5));
            employees.add(employee);
            employeeRepo.save(employee);
        }
    }

    @AfterEach
    void clearDb(){
        //employeeRepo.deleteAll();
        //departmentRepo.deleteAll();
    }

    @Test
    void test1(){
        //departmentRepo.findAll().forEach(System.out::println);
        employeeRepo.findAll().forEach(e -> printEmployee(e));
    }


    void printEmployee(Employee employee){
        String name = employee.getName();
        Department department = employee.getDepartment();
        //System.out.println(department.getClass());
        System.out.println(name + " works in " + department.getName());
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
