package com.uh.admin_portal.controller;

import com.uh.admin_portal.resource.LinkResource;
import com.uh.admin_portal.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/links")
    public ResponseEntity createLink(@RequestBody LinkResource linkResource) {
        LinkResource createdLink = linkService.createLink(linkResource);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdLink);
    }

    @GetMapping("/links")
    public ResponseEntity getLinks() {
        List<LinkResource> linkResources = linkService.getLinks();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(linkResources);
    }

    @GetMapping("/links/{role}")
    public ResponseEntity getLinksForRole(@PathVariable("role") String role) {
        List<LinkResource> linkResources = linkService.getLinksForRole(role);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(linkResources);
    }

    @PutMapping("/links/{linkId}")
    public ResponseEntity updateLink(@PathVariable("linkId") Long linkId, @RequestBody LinkResource linkResource) {
        LinkResource updatedLink = linkService.updateLink(linkId, linkResource);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(updatedLink);
    }

    @DeleteMapping("/links/{linkId}")
    public ResponseEntity deleteLink(@PathVariable("linkId") Long linkId) {
        linkService.deleteLink(linkId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Deleted Link");
    }
}
