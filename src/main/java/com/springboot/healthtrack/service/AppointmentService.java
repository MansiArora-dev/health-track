package com.springboot.healthtrack.service;

import com.springboot.healthtrack.entity.Appointment;

public interface AppointmentService {

    Appointment createANewAppointment(Appointment appointment, Long patientId, Long doctorId);

}