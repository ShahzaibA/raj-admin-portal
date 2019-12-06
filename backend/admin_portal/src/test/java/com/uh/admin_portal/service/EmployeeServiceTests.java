package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Employee;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.EmployeeRepository;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.EmployeeResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmployeeServiceTests {

    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeResource employeeResourceCreated;
    private EmployeeResource employeeResourceToCreate;

    private List<Employee> employees;
    private Employee employeeCreated;

    private Role role;

    @Before
    public void init() {
        // create employee test init
        employeeResourceToCreate = new EmployeeResource("Test Name", "test@test.com", "Test Password");
        employeeResourceCreated = new EmployeeResource();
        employeeResourceCreated = employeeResourceToCreate;
        employeeResourceCreated.setId(1);
        employeeCreated = new Employee(employeeResourceCreated);
        employeeCreated.setId(1L);
        employees  = new ArrayList<>();
        employees.add(employeeCreated);
        role = new Role();
        role.setRole("ADMIN");
    }

    @Test
    public void createEmployee_OnSuccess_Return() throws Exception {
        when(employeeRepository.saveAndFlush(any(Employee.class)))
                .thenReturn(employeeCreated);
        assertEquals(employeeService.createEmployee(employeeResourceToCreate), employeeService.createEmployee(employeeResourceToCreate));
    }

    @Test
    public void getEmployee_OnSuccess_Return() throws Exception {
        when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.of(employeeCreated));
        assertEquals(employeeService.getEmployee(1L), employeeService.getEmployee(1L));
    }

    @Test
    public void getEmployees_OnSuccess_Return() throws Exception {
        when(employeeRepository.findAll())
                .thenReturn(employees);
        assertEquals(employeeService.getEmployees(), employeeService.getEmployees());
    }

    @Test
    public void updateEmployeeRole_OnSuccess_Return() throws Exception {
        when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.of(employeeCreated));
        when(roleRepository.findByRoleEquals(anyString()))
                .thenReturn(Optional.of(role));
        when(employeeRepository.saveAndFlush(any(Employee.class)))
                .thenReturn(employeeCreated);
        assertEquals(employeeService.updateEmployeeRole(1L, role), employeeService.updateEmployeeRole(1L, role));
    }

    @Test
    public void loginEmployee_OnSuccess_Return() throws Exception {
        when(employeeRepository.findByEmailAndPassword(anyString(), anyString()))
                .thenReturn(Optional.of(employeeCreated));
        assertEquals(employeeService.loginEmployee(employeeResourceCreated), employeeService.loginEmployee(employeeResourceCreated));
    }
}
