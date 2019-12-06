package com.uh.admin_portal.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uh.admin_portal.model.Link;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LinkResource {
    public static final String RESOURCE_NAME = "Link";

    @JsonProperty("link_id")
    private Long id;

    @JsonProperty("link")
    @NotNull(message = "A link must be provided.")
    private String link;

    @JsonProperty("role")
    @NotNull(message = "An employee role must be provided.")
    private String role;

    public LinkResource() {}

    public LinkResource(LinkResource linkResource) {
        this.link = linkResource.getLink();
        this.role = linkResource.getRole();
    }

    public LinkResource(Link link) {
        this.id = link.getId();
        this.link = link.getLink();
        this.role = link.getRole().getRole();
    }
}
