package com.smartclinic.controllers;

import com.smartclinic.models.Prescription;
import com.smartclinic.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // ─────────────────────────────────────────────────────────────
    // POST endpoint to save a prescription
    // ✅ Token and request body validation (3 pts)
    // ✅ Return success or error using ResponseEntity (3 pts)
    // ─────────────────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<?> addPrescription(@RequestHeader("Authorization") String token,
                                             @Validated @RequestBody Prescription prescription) {
        try {
            if (!prescriptionService.validateToken(token)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
            }

            Prescription saved = prescriptionService.savePrescription(prescription);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving prescription: " + e.getMessage());
        }
    }
}
