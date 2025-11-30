package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "medical_records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalRecordId;

    @Column(nullable = false)
    private Long consultationId;

//    for faster history lookup
    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private String diagnosis;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id", referencedColumnName = "prescriptionId")
    private Prescription prescription;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

}
