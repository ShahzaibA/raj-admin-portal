package com.uh.admin_portal.repository;

import com.uh.admin_portal.model.Link;
import com.uh.admin_portal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
//    List<Link> findAllByRoleEquals(Role roleFound);

    List<Link> findAllByRoleEqualsOrRoleEquals(Role roleFound, Role adminRole);
}
