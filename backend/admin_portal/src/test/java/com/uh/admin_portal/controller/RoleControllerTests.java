package com.uh.admin_portal.controller;

import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.resource.RoleResource;
import com.uh.admin_portal.service.RoleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoleControllerTests {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    private RoleResource roleResourceToCreate;
    private RoleResource roleResourceCreated;

    private List<RoleResource> roleResources;

    @Before
    public void init() {
        roleResourceToCreate = new RoleResource();
        roleResourceToCreate.setRole("Test Admin");
        roleResourceCreated = new RoleResource();
        roleResourceCreated = roleResourceToCreate;
        roleResourceCreated.setId(1L);
        roleResources = new ArrayList<>();
        roleResources.add(roleResourceCreated);
    }

    @Test
    public void createRole_onSuccess_returnHttpStatusCode201() {
        when(roleService.createRole(any(RoleResource.class)))
                .thenReturn(roleResourceCreated);
        assertEquals(roleResourceCreated, roleController.createRole(roleResourceToCreate).getBody());
    }

    @Test
    public void getRole_onSuccess_returnHttpStatusCode200() {
        when(roleService.getRole(anyLong()))
                .thenReturn(roleResourceCreated);
        assertEquals(roleResourceCreated, roleController.getRole(1L).getBody());
    }

    @Test
    public void getRoles_onSuccess_returnHttpStatusCode200() {
        when(roleService.getRoles())
                .thenReturn(roleResources);
        assertEquals(roleResources, roleController.getRoles().getBody());
    }
}
