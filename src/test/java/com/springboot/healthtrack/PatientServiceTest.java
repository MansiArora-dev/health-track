package com.springboot.healthtrack;

import com.springboot.healthtrack.entity.Patient;
import com.springboot.healthtrack.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient() {
//        List<Patient> patientList = patientRepository.findAll();
//        List<CPatientInfo> patientList = patientRepository.getAllPatientsInfoConcrete();
//        List<BloodGroupStats> patientList = patientRepository.getBloodGroupStats();
//
//        for(var p: patientList) {
//            System.out.println(p);
//        }

//        int rowsAffected = patientRepository.updatePatientNameWithId("Mansi Arora", 1L);
//
//        System.out.println(rowsAffected);

        List<Patient> patientList = patientRepository.getAllPatientsWithAppointments();

        for(var p: patientList) {
            System.out.println(p);
        }
    }
}