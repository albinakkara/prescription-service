package com.example.demo.service;

import com.example.demo.client.DoctorClient;
import com.example.demo.client.PatientClient;
import com.example.demo.dto.MedicalRecordRequest;
import com.example.demo.entity.MedicalRecord;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MedicalRecordRepository;
import com.example.demo.entity.Prescription;
import com.example.demo.entity.PrescriptionItem;
import org.springframework.web.server.ResponseStatusException;
//import com.example.demo.client.NotificationClient;
import java.util.List;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository repo;
    private final PatientClient patientClient;
    private final DoctorClient doctorClient;
//    private NotificationClient notification;

    MedicalRecordService(MedicalRecordRepository repo, PatientClient patientClient, DoctorClient doctorClient){
        this.repo = repo;
        this.doctorClient = doctorClient;
        this.patientClient = patientClient;
    }

    public Long createMedicalRecordWithPrescription(MedicalRecordRequest request) {
//        validate patient and doctor ids
        assertDoctorExists(request.getDoctorId());
        assertPatientExists(request.getPatientId());

        // Create MedicalRecord
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setConsultationId(request.getConsultationId());
        medicalRecord.setPatientId(request.getPatientId());
        medicalRecord.setDoctorId(request.getDoctorId());
        medicalRecord.setDiagnosis(request.getDiagnosis());

        // Create Prescription
        Prescription prescription = new Prescription();

        // Map Prescription Items
        List<PrescriptionItem> items = request.getPrescription().getItems().stream()
                .map(i -> {
                    PrescriptionItem item = new PrescriptionItem();
                    item.setMedicineName(i.getMedicineName());
                    item.setDosage(i.getDosage());
                    item.setFrequency(i.getFrequency());
                    item.setDurationDays(i.getDurationDays());
                    item.setPrescription(prescription); // Link back
                    return item;
                })
                .toList();

        prescription.setItems(items);

        // Link Prescription to MedicalRecord
        medicalRecord.setPrescription(prescription);

        // Save MedicalRecord (Cascade will save Prescription and Items)
        return repo.save(medicalRecord).getMedicalRecordId();
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return repo.findAll();
    }

    private void assertPatientExists(Long patientId) {
        try {
            patientClient.validatePatientWithId(patientId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Patient not found");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Patient service unavailable", e);
        }
    }

    private void assertDoctorExists(Long doctorId) {
        try {
            doctorClient.validateDoctorWithId(doctorId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Doctor not found");
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Doctor service unavailable", e);
        }
    }

//    public List<Prescription> getPatientPrescriptions(Long patientId) {
//        return repo.findByPatientId(patientId);
//    }
//
//    public List<Prescription> getDoctorPrescriptions(Long doctorId) {
//        return repo.findByDoctorId(doctorId);
//    }

//    public Prescription updateStatus(Long id, String status) {
//        Prescription p = repo.findById(id).orElseThrow();
//        p.setStatus(status);
//        return repo.save(p);
//    }
}