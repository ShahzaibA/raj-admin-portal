package com.uh.admin_portal.model;

import com.uh.admin_portal.resource.RoleResource;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", unique = true)
    private String role;

    @OneToMany(mappedBy="role", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Employee> employees;

    @OneToMany(mappedBy="role", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Link> links;

    public Role() {}

    public Role(RoleResource roleResource) {
        this.role = roleResource.getRole();
    }
}