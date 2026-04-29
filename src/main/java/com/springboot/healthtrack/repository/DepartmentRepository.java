package com.springboot.healthtrack.repository;

import com.springboot.healthtrack.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}