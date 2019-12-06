package com.uh.admin_portal.service;

import com.uh.admin_portal.model.Link;
import com.uh.admin_portal.model.Role;
import com.uh.admin_portal.repository.LinkRepository;
import com.uh.admin_portal.repository.RoleRepository;
import com.uh.admin_portal.resource.LinkResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private RoleRepository roleRepository;

    public LinkResource createLink(LinkResource linkResource) {
        Role role = roleRepository.findByRoleEquals(linkResource.getRole()).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));

        Link linkToCreate = new Link(linkResource);
        linkToCreate.setRole(role);
        Link createdLink = linkRepository.saveAndFlush(linkToCreate);

        return new LinkResource(createdLink);
    }

    public List<LinkResource> getLinks() {
        List<LinkResource> linkResources = linkRepository.findAll()
                .stream()
                .map(link -> {
                    return new LinkResource(link);
                })
                .collect(Collectors.toList());

        return linkResources;
    }

    public List<LinkResource> getLinksForRole(String role) {
        Role roleFound = roleRepository.findByRoleEquals(role).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        Role adminRole = roleRepository.findByRoleEquals("ADMIN").orElseThrow(() ->
                new EntityNotFoundException("Role not found."));
        List<LinkResource> linkResources = linkRepository.findAllByRoleEqualsOrRoleEquals(roleFound, adminRole)
                .stream()
                .map(link -> {
                    return new LinkResource(link);
                })
                .collect(Collectors.toList());
        return linkResources;
    }

    public LinkResource updateLink(Long linkId, LinkResource linkResource) {
        Link link = linkRepository.findById(linkId).orElseThrow(() ->
                new EntityNotFoundException("Link not found."));
        Role role = roleRepository.findByRoleEquals(linkResource.getRole()).orElseThrow(() ->
                new EntityNotFoundException("Role not found."));

        link.setLink(linkResource.getLink());
        link.setRole(role);
        Link updatedLink = linkRepository.saveAndFlush(link);

        return new LinkResource(updatedLink);
    }

    public void deleteLink(Long linkId) {
        Link link = linkRepository.findById(linkId).orElseThrow(() ->
                new EntityNotFoundException("Link not found."));
        linkRepository.delete(link);
    }
}
