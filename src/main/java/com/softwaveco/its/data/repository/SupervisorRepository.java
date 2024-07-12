package com.softwaveco.its.data.repository;

import com.softwaveco.its.data.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, String> {
}

