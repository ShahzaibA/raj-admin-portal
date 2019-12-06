package com.uh.admin_portal.controller;

import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.resource.EmployeeResource;
import com.uh.admin_portal.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class EmployeeControllerTests {

    @Mock
    EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private EmployeeResource employeeResourceCreated;
    private EmployeeResource employeeResourceToCreate;
    private EmployeeResource employeeResourceUpdated;

    private List<EmployeeResource> employeeResources;

    private Role role;


    @Before
    public void init() {
        // create employee test init
        employeeResourceToCreate = new EmployeeResource("Test Name", "test@test.com", "TestPassword");
        employeeResourceCreated = new EmployeeResource();
        employeeResourceCreated = employeeResourceToCreate;
        employeeResourceCreated.setId(1);
        employeeResourceCreated.setRole("Unassigned");

        // get all employees test
        employeeResources = new ArrayList<>();
        employeeResources.add(employeeResourceCreated);

        // updated employee test
        employeeResourceUpdated = new EmployeeResource(employeeResourceCreated);
        Role role = new Role();
        role.setId(1L);
        role.setRole("FINANCE_ADMIN");
        employeeResourceUpdated.setRole(role.getRole());
    }

    @Test
    public void createEmployee_OnSuccess_ReturnHttpStatusCode201() throws Exception {
        when(employeeService.createEmployee(any(EmployeeResource.class)))
                .thenReturn(employeeResourceCreated);
        assertEquals(employeeResourceCreated, employeeController.createEmployee(employeeResourceToCreate).getBody());
    }

    @Test
    public void getEmployee_onSuccess_returnHttpStatusCode200() {
        when(employeeService.getEmployee(anyLong()))
                .thenReturn(employeeResourceCreated);
        assertEquals(employeeResourceCreated, employeeController.getEmployee(anyLong()).getBody());
    }

    @Test
    public void geEmployees_onSuccess_returnHttpStatusCode200() {
        when(employeeService.getEmployees())
                .thenReturn(employeeResources);
        assertEquals(employeeResources, employeeController.getEmployees().getBody());
    }

    @Test
    public void updateEmployeeRole_onSuccess_returnHttpStatusCode200() {
        when(employeeService.updateEmployeeRole(1L, role))
                .thenReturn(employeeResourceUpdated);
        assertEquals(employeeResourceUpdated, employeeController.updateEmployeeRole(1L, role).getBody());
    }

    @Test
    public void loginEmployee_onSuccess_returnHttpStatusCode200() {
        when(employeeService.loginEmployee(any(EmployeeResource.class)))
                .thenReturn(employeeResourceCreated);
        assertEquals(employeeResourceCreated, employeeController.loginEmployee(employeeResourceCreated).getBody());
    }

}
