package com.uh.admin_portal.controller;

import com.uh.admin_portal.resource.LinkResource;
import com.uh.admin_portal.service.LinkService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LinkControllerTests {

    @Mock
    private LinkService linkService;

    @InjectMocks
    private LinkController linkController;

    private LinkResource linkResourceToCreate;
    private LinkResource linkResourceCreated;

    private List<LinkResource> linkResources;

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
    }

    @Test
    public void createLink_onSuccess_returnHttpStatusCode201() {
        when(linkService.createLink(any(LinkResource.class)))
                .thenReturn(linkResourceCreated);
        assertEquals(linkResourceCreated, linkController.createLink(linkResourceToCreate).getBody());
    }

    @Test
    public void getLinks_onSuccess_returnHttpStatusCode200() {
        when(linkService.getLinks())
                .thenReturn(linkResources);
        assertEquals(linkResources, linkController.getLinks().getBody());
    }

    @Test
    public void getLinksForRole_onSuccess_returnHttpStatusCode200() {
        when(linkService.getLinksForRole(anyString()))
                .thenReturn(linkResources);
        assertEquals(linkResources, linkController.getLinksForRole("Test String").getBody());
    }

    @Test
    public void updateLink_onSuccess_returnHttpStatusCode201() {
        when(linkService.updateLink(anyLong(), any(LinkResource.class)))
                .thenReturn(linkResourceCreated);
        assertEquals(linkResourceCreated, linkController.updateLink(1L, linkResourceCreated).getBody());
    }

    @Test
    public void deleteLink_onSuccess_returnHttpStatusCode204() {
        assertEquals(HttpStatus.NO_CONTENT, linkController.deleteLink(1L).getStatusCode());
    }
}
