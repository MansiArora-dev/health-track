package com.springboot.healthtrack.repository;

import com.springboot.healthtrack.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}