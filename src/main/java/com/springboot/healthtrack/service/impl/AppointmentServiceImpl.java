package com.springboot.healthtrack.service.impl;

import com.springboot.healthtrack.entity.Appointment;
import com.springboot.healthtrack.entity.Doctor;
import com.springboot.healthtrack.entity.Patient;
import com.springboot.healthtrack.repository.AppointmentRepository;
import com.springboot.healthtrack.repository.DoctorRepository;
import com.springboot.healthtrack.repository.PatientRepository;
import com.springboot.healthtrack.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Appointment createANewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;
    }

}