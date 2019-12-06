package com.uh.admin_portal.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uh.admin_portal.model.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RoleResource {
    public static final String RESOURCE_NAME = "Role";

    @JsonProperty("role_id")
    private Long id;

    @JsonProperty("role")
    @NotNull(message = "An employee role must be provided.")
    private String role;

    public RoleResource() {}

    public RoleResource(Role role) {
        this.id = role.getId();
        this.role = role.getRole();
    }
}
