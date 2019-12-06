package com.uh.admin_portal.controller;

import com.uh.admin_portal.resource.RoleResource;
import com.uh.admin_portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/roles")
    public ResponseEntity createRole(@RequestBody RoleResource roleResource) {
        RoleResource createdRoleResource = roleService.createRole(roleResource);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdRoleResource);
    }

    @DeleteMapping("/roles/{roleId}")
    public ResponseEntity deleteRole(@PathVariable long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted");
    }

    @PutMapping("/roles/{roleId}")
    public ResponseEntity modifyRole(@PathVariable long roleId, @RequestBody RoleResource roleResource) {
        RoleResource modifiedRoleResource = roleService.modifyRole(roleId, roleResource);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(modifiedRoleResource);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity getRole(@PathVariable long id) {
        RoleResource roleResource = roleService.getRole(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleResource);
    }

    @GetMapping("/roles")
    public ResponseEntity getRoles(){
        List<RoleResource> roleResources = roleService.getRoles();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roleResources);
    }
}
