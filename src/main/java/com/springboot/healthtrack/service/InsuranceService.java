package com.springboot.healthtrack.service;

import com.springboot.healthtrack.entity.Insurance;
import com.springboot.healthtrack.entity.Patient;

public interface InsuranceService {

    Insurance assignInsuranceToPatient(Insurance insurance, Long patientId);

    Insurance updateInsuranceOfAPatient(Insurance insurance, Long patientId);

    Patient removeInsuranceOfAPatient(Long patientId);

}