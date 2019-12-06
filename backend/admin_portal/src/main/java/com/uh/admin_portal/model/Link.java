package com.uh.admin_portal.model;

import com.uh.admin_portal.resource.LinkResource;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "link", unique = true)
    private String link;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Link() {}

    public Link(LinkResource linkResource) {
        this.link = linkResource.getLink();
    }
}
