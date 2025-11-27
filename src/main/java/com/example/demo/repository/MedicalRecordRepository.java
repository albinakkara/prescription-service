package com.example.demo.repository;

import com.example.demo.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Prescription;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}

