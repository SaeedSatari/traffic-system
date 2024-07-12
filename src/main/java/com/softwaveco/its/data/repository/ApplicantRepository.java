package com.softwaveco.its.data.repository;

import com.softwaveco.its.data.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
}

