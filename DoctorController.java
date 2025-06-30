package com.smartclinic.controllers;

import com.smartclinic.models.Doctor;
import com.smartclinic.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "*")  // Allow access from frontend
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // ─────────────────────────────────────────────────────────────
    // GET: All doctors
    // Endpoint: /doctors/all
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // ─────────────────────────────────────────────────────────────
    // POST: Register a new doctor (Admin role)
    // ─────────────────────────────────────────────────────────────
    @PostMapping("/register")
    public Doctor registerDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    // ─────────────────────────────────────────────────────────────
    // GET: Doctors by speciality and availability (for patient search)
    // ─────────────────────────────────────────────────────────────
    @GetMapping("/search")
    public List<Doctor> searchDoctors(
            @RequestParam String speciality,
            @RequestParam(required = false) String availableTime) {
        return doctorService.searchBySpecialityAndTime(speciality, availableTime);
    }
}

