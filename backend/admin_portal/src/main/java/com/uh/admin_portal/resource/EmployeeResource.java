package com.uh.admin_portal.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uh.admin_portal.model.Employee;
import lombok.Data;

@Data
public class EmployeeResource {
    public static final String RESOURCE_NAME = "Employee";

    @JsonProperty("employee_id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("role")
    private String role;

    @JsonProperty("password")
    private String password;

    public EmployeeResource() {}

    public EmployeeResource(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public EmployeeResource(EmployeeResource employeeResource) {
        this.id = employeeResource.getId();
        this.name = employeeResource.getName();
        this.email = employeeResource.getEmail();
        this.role = employeeResource.getRole();
        this.password = employeeResource.getPassword();
    }

    public EmployeeResource(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        if (employee.getRole() != null){
            this.role = employee.getRole().getRole();
        }
        else {
            this.role = "UNASSIGNED";
        }
    }

}
