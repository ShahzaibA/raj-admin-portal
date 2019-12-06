package com.uh.admin_portal.repository;

import com.uh.admin_portal.model.Employee;
import com.uh.admin_portal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmailAndPassword(String email, String password);

    List<Employee> findAllByRoleEquals(Role role);
}
