package com.example.demo.dto;

import lombok.Data;

@Data
public class MedicalRecordRequest {

    private Long consultationId;
    private Long patientId;
    private Long doctorId;
    private String diagnosis;

    private PrescriptionRequest prescription;

}

