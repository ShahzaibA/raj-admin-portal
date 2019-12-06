package com.uh.admin_portal.model;

import com.uh.admin_portal.resource.EmployeeResource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Employee() {}

    public Employee(EmployeeResource employeeResource) {
        this.name = employeeResource.getName();
        this.email = employeeResource.getEmail();
        this.password = employeeResource.getPassword();
    }
}
