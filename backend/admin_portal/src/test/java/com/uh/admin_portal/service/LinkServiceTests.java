package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Link;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.LinkRepository;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.LinkResource;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LinkServiceTests {

    @Mock
    LinkRepository linkRepository;

    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    LinkService linkService;

    private LinkResource linkResourceToCreate;
    private LinkResource linkResourceCreated;

    private Link link;

    private List<LinkResource> linkResources;
    private List<Link> links;

    private Role role;


    @Before
    public void init() {
        // create link test
        linkResourceToCreate = new LinkResource();
        linkResourceToCreate.setLink("testLink");
        linkResourceToCreate.setRole("Test_Admin");
        linkResourceCreated = new LinkResource(linkResourceToCreate);
        linkResourceCreated.setId(1L);


        // get links test
        linkResources = new ArrayList<>();
        linkResources.add(linkResourceCreated);

        link = new Link(linkResourceCreated);
        role = new Role();
        role.setRole("ADMIN");
        link.setRole(role);
        link.setId(1L);
        links = new ArrayList<>();
        links.add(link);
    }

    @Test
    public void createLink_onSuccess_return() {
        when(roleRepository.findByRoleEquals(anyString()))
                .thenReturn(Optional.of(role));
        when(linkRepository.saveAndFlush(any(Link.class)))
                .thenReturn(link);
        assertEquals(linkService.createLink(linkResourceToCreate), linkService.createLink(linkResourceToCreate));
    }

    @Test
    public void getLinks_onSuccess_return() {
        when(linkRepository.findAll())
                .thenReturn(links);
        assertEquals(linkService.getLinks(), linkService.getLinks());
    }

    @Test
    public void getLinksForRole_onSuccess_return() {
        when(roleRepository.findByRoleEquals(anyString()))
                .thenReturn(Optional.of(role));
        when(roleRepository.findByRoleEquals("ADMIN"))
                .thenReturn(Optional.of(role));
        when(linkRepository.findAllByRoleEqualsOrRoleEquals(any(Role.class), any(Role.class)))
                .thenReturn(links);
        assertEquals(linkService.getLinksForRole("Test"), linkService.getLinksForRole("Test"));
    }

    @Test
    public void updateLink_onSuccess_return() {
        when(linkRepository.findById(anyLong()))
                .thenReturn(Optional.of(link));
        when(roleRepository.findByRoleEquals(anyString()))
                .thenReturn(Optional.of(role));
        when(linkRepository.saveAndFlush(any(Link.class)))
                .thenReturn(link);
        assertEquals(linkService.updateLink(1L, linkResourceCreated), linkService.updateLink(1L, linkResourceCreated));
    }

    @Test
    public void deleteLink_onSuccess_return() {
        when(linkRepository.findById(anyLong()))
                .thenReturn(Optional.of(link));
    }

}
