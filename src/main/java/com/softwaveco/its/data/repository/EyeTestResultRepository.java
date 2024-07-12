package com.softwaveco.its.data.repository;

import com.softwaveco.its.data.entity.EyeTestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EyeTestResultRepository extends JpaRepository<EyeTestResult, String> {
}
