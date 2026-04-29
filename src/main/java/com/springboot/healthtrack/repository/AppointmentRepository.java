package com.springboot.healthtrack.repository;

import com.springboot.healthtrack.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
