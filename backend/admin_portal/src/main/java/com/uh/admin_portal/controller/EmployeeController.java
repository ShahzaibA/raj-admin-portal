package com.uh.admin_portal.controller;

import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.resource.EmployeeResource;
import com.uh.admin_portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity createEmployee(@RequestBody EmployeeResource employeeResource) {
        EmployeeResource createdEmployee = employeeService.createEmployee(employeeResource);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEmployee);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable("employeeId") Long employeeId) {
        EmployeeResource employeeResource = employeeService.getEmployee(employeeId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResource);
    }

    @GetMapping("/employees")
    public ResponseEntity getEmployees() {
        List<EmployeeResource> employeeResources = employeeService.getEmployees();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeResources);
    }

    @PutMapping("employees/{employeeId}/role")
    public ResponseEntity updateEmployeeRole(@PathVariable("employeeId") Long employeeId, @RequestBody Role role) {
        EmployeeResource updatedEmployeeResource = employeeService.updateEmployeeRole(employeeId, role);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedEmployeeResource);
    }

    @PostMapping("employees/login")
    public ResponseEntity loginEmployee(@RequestBody EmployeeResource employeeResource) {
        EmployeeResource foundEmployee = employeeService.loginEmployee(employeeResource);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(foundEmployee);
    }
}
