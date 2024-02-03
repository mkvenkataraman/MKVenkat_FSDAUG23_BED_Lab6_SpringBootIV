package com.debate.registration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.debate.registration.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(String name);
}
