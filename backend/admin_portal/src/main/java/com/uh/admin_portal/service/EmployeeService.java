package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Employee;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.EmployeeRepository;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RoleRepository roleRepository;

    public EmployeeResource createEmployee(EmployeeResource employeeResource) {
        Employee employeeToCreate = new Employee(employeeResource);
        Employee createdEmployee = employeeRepository.saveAndFlush(employeeToCreate);

        return new EmployeeResource(createdEmployee);
    }

    public EmployeeResource getEmployee(Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return new EmployeeResource(employee.get());
    }

    public List<EmployeeResource> getEmployees() {
        List<EmployeeResource> employeeResources = employeeRepository.findAll()
                .stream()
                .map(employee -> {
                    return new EmployeeResource(employee);
                })
                .collect(Collectors.toList());

        return employeeResources;
    }

    public EmployeeResource updateEmployeeRole(Long employeeId, Role role) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new EntityNotFoundException("Employee not found."));
        Role foundRole = roleRepository.findByRoleEquals(role.getRole()).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        employee.setRole(foundRole);
        Employee updatedEmployee = employeeRepository.saveAndFlush(employee);
        return new EmployeeResource(updatedEmployee);
    }

    public EmployeeResource loginEmployee(EmployeeResource employeeResource) {
        Employee employee = employeeRepository.findByEmailAndPassword(employeeResource.getEmail(), employeeResource.getPassword()).orElseThrow(() ->
                new EntityNotFoundException("Employee not found."));
        return new EmployeeResource(employee);
    }
}
