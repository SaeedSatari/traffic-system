package com.softwaveco.its.data.repository;


import com.softwaveco.its.data.entity.Role;
import com.softwaveco.its.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}