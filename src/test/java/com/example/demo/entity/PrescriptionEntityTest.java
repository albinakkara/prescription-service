package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrescriptionEntityTest {

    @Test
    void prescription_gettersAndSetters_workCorrectly() {
        Prescription prescription = new Prescription();

        Long prescriptionId = 100L;
        prescription.setPrescriptionId(prescriptionId);

        assertEquals(prescriptionId, prescription.getPrescriptionId());
    }

    @Test
    void prescription_itemsList_isInitializedAndMutable() {
        Prescription prescription = new Prescription();

        // Lombok @Data initializes items as new ArrayList<>()
        assertNotNull(prescription.getItems());
        assertTrue(prescription.getItems().isEmpty());

        PrescriptionItem item = new PrescriptionItem();
        item.setMedicineName("Paracetamol");
        item.setDosage("500mg");

        List<PrescriptionItem> items = new ArrayList<>();
        items.add(item);

        prescription.setItems(items);

        assertEquals(1, prescription.getItems().size());
        assertEquals("Paracetamol", prescription.getItems().get(0).getMedicineName());
        assertEquals("500mg", prescription.getItems().get(0).getDosage());
    }
}
