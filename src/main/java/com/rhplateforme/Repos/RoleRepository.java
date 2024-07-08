package com.rhplateforme.Repos;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rhplateforme.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}

