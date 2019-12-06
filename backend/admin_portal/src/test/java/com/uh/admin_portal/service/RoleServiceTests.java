package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Link;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.RoleResource;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoleServiceTests {

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleService roleService;

    private RoleResource roleResourceToCreate;
    private RoleResource roleResourceCreated;

    private List<RoleResource> roleResources;

    private List<Role> roles;

    private Link link;
    private Role role;

    @Before
    public void init() {
        roleResourceToCreate = new RoleResource();
        roleResourceToCreate.setRole("Test Admin");
        roleResourceCreated = new RoleResource();
        roleResourceCreated = roleResourceToCreate;
        roleResourceCreated.setId(1L);
        roleResources = new ArrayList<>();
        roleResources.add(roleResourceCreated);
        role = new Role();
        link = new Link();
        role.setRole("ADMIN");
        role.setId(1L);
        link.setRole(role);
        link.setId(1L);
        roles = new ArrayList<>();
        roles.add(role);
    }

    @Test
    public void createRole_onSuccess_return() {
        when(roleRepository.saveAndFlush(any(Role.class)))
                .thenReturn(role);
        assertEquals(roleService.createRole(roleResourceToCreate), roleService.createRole(roleResourceToCreate));
    }

    @Test
    public void getRole_onSuccess_return() {
        when(roleRepository.findById(anyLong()))
                .thenReturn(Optional.of(role));
        assertEquals(roleService.getRole(1L), roleService.getRole(1L));
    }

    @Test
    public void getRoles_onSuccess_return() {
        when(roleRepository.findAll())
                .thenReturn(roles);
        assertEquals(roleService.getRoles(), roleService.getRoles());
    }

    @Test
    public void deleteRole_onSuccess_void() {
        when(roleRepository.findById(anyLong()))
                .thenReturn(Optional.of(role));
    }

    @Test
    public void modifyRole_onSuccess_return() {
        when(roleRepository.findById(anyLong()))
                .thenReturn(Optional.of(role));
        when(roleRepository.saveAndFlush(any(Role.class)))
                .thenReturn(role);
        assertEquals(roleService.modifyRole(1L, roleResourceCreated), roleService.modifyRole(1L, roleResourceCreated));
    }
}
