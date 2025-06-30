package com.smartclinic.services;

import com.smartclinic.models.Appointment;
import com.smartclinic.repositories.AppointmentRepository;
import com.smartclinic.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TokenService tokenService;

    // ─────────────────────────────────────────────────────────────
    // Save a new appointment
    // Used by controllers after validating input and tokens
    // ─────────────────────────────────────────────────────────────
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // ─────────────────────────────────────────────────────────────
    // Get all appointments for a patient (used with token auth)
    // ─────────────────────────────────────────────────────────────
    public List<Appointment> getAppointmentsByPatientEmail(String email) {
        return appointmentRepository.findByPatientEmail(email);
    }

    // ─────────────────────────────────────────────────────────────
    // Get all appointments for a doctor
    // ─────────────────────────────────────────────────────────────
    public List<Appointment> getAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // ─────────────────────────────────────────────────────────────
    // Validate patient token and extract email
    // ─────────────────────────────────────────────────────────────
    public String validateAndExtractEmail(String token) {
        if (tokenService.isValid(token)) {
            return tokenService.extractEmail(token);
        }
        return null;
    }
}
