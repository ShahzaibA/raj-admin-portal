package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Employee;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.EmployeeRepository;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public RoleResource createRole(RoleResource roleResource) {
        Role roleToCreate = new Role(roleResource);
        Role createdRole = roleRepository.saveAndFlush(roleToCreate);

        return new RoleResource(createdRole);
    }

    public RoleResource getRole(final long id) {
        Optional<Role> role = roleRepository.findById(id);
        return new RoleResource(role.get());
    }

    public List<RoleResource> getRoles() {
        List<RoleResource> roleResources = roleRepository.findAll()
                .stream()
                .map(role -> {
                    return new RoleResource(role);
                })
                .collect(Collectors.toList());

        return roleResources;
    }

    public void deleteRole(long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        Role unassigned = roleRepository.findByRoleEquals("UNASSIGNED").orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        List<Employee> employees = employeeRepository.findAllByRoleEquals(role);
        for(Employee employee : employees) {
            employee.setRole(unassigned);
            employeeRepository.saveAndFlush(employee);
        }
        roleRepository.delete(role);
    }

    public RoleResource modifyRole(long roleId, RoleResource roleResource) {
        Role role = roleRepository.findById(roleId).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        role.setRole(roleResource.getRole());
        Role modifiedRole = roleRepository.saveAndFlush(role);

        return new RoleResource(modifiedRole);
    }
}
