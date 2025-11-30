package com.example.demo.controller;

import com.example.demo.dto.MedicalRecordRequest;
import com.example.demo.entity.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.MedicalRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody MedicalRecordRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createMedicalRecordWithPrescription(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicalRecord>> getAllMedicalRecords(@RequestParam Long patientId){
        return ResponseEntity.ok(service.getMedicalRecordsByPatientId(patientId));
    }

//    @GetMapping("/patient/{patientId}")
//    public List<Prescription> getForPatient(@PathVariable Long patientId) {
//        return service.getPatientPrescriptions(patientId);
//    }
//
//    @GetMapping("/doctor/{doctorId}")
//    public List<Prescription> getForDoctor(@PathVariable Long doctorId) {
//        return service.getDoctorPrescriptions(doctorId);
//    }

//    @PutMapping("/{id}/status")
//    public Prescription updateStatus(
//            @PathVariable Long id,
//            @RequestParam String status
//    ) {
//        return service.updateStatus(id, status);
//    }
}
