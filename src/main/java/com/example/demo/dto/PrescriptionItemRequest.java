package com.example.demo.dto;

import lombok.Data;

@Data
public class PrescriptionItemRequest {
    private String medicineName;
    private String dosage;
    private String frequency;
    private Integer durationDays;
}
