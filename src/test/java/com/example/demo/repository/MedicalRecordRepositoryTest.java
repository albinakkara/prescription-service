package com.example.demo.repository;

import com.example.demo.entity.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedicalRecordRepositoryTest {

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @Test
    void findMedicalRecordsByPatientId_returnsList() {
        Long patientId = 1L;

        MedicalRecord record = new MedicalRecord();
        record.setPatientId(patientId);

        List<MedicalRecord> mockResult = Collections.singletonList(record);

        when(medicalRecordRepository.findMedicalRecordsByPatientId(patientId))
                .thenReturn(mockResult);

        List<MedicalRecord> result = medicalRecordRepository.findMedicalRecordsByPatientId(patientId);

        assertEquals(1, result.size());
        assertEquals(patientId, result.get(0).getPatientId());
    }
}
