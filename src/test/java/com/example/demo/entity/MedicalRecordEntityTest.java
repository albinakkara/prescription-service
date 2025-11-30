package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordEntityTest {

    @Test
    void medicalRecord_gettersAndSetters_workCorrectly() {
        MedicalRecord record = new MedicalRecord();

        Long medicalRecordId = 1L;
        Long consultationId = 10L;
        Long patientId = 20L;
        Long doctorId = 30L;
        String diagnosis = "Test Diagnosis";

        Prescription prescription = new Prescription();

        record.setMedicalRecordId(medicalRecordId);
        record.setConsultationId(consultationId);
        record.setPatientId(patientId);
        record.setDoctorId(doctorId);
        record.setDiagnosis(diagnosis);
        record.setPrescription(prescription);

        // createdAt is @CreationTimestamp in real DB, but we can still set it manually for test
        LocalDateTime now = LocalDateTime.now();
        record.setCreatedAt(now);

        assertEquals(medicalRecordId, record.getMedicalRecordId());
        assertEquals(consultationId, record.getConsultationId());
        assertEquals(patientId, record.getPatientId());
        assertEquals(doctorId, record.getDoctorId());
        assertEquals(diagnosis, record.getDiagnosis());
        assertEquals(prescription, record.getPrescription());
        assertEquals(now, record.getCreatedAt());
    }
}
