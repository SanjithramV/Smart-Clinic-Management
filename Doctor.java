package com.smartclinic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * JPA entity that represents a doctor in the Smart Clinic Management System.
 */
@Entity
@Table(name = "doctor")
public class Doctor {

    // ──────────────────────────────────────────────────────────────────────────────
    // Primary Key
    // ──────────────────────────────────────────────────────────────────────────────
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      // ✅ Primary‑key annotation
    private Long id;

    // ──────────────────────────────────────────────────────────────────────────────
    // Basic Fields
    // ──────────────────────────────────────────────────────────────────────────────
    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Email
    @Size(max = 120)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 80)
    private String specialty;

    /**
     * Encrypted password (write‑only in JSON responses)
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 60, max = 60)   // e.g., BCrypt hash length
    private String passwordHash;

    // ──────────────────────────────────────────────────────────────────────────────
    // Availability – satisfies “availableTimes” requirement (List<String>)
    // ──────────────────────────────────────────────────────────────────────────────
    /**
     * A list of available time slots in ISO‑8601 format (e.g., “10:00”, “14:30”)
     */
    @ElementCollection                                           // ✅ Correct annotation
    @CollectionTable(
        name = "doctor_available_times",
        joinColumns = @JoinColumn(name = "doctor_id")
    )
    @Column(name = "time_slot", nullable = false, length = 5)
    private List<String> availableTimes;                         // ✅ Required field

    // ──────────────────────────────────────────────────────────────────────────────
    // Constructors
    // ──────────────────────────────────────────────────────────────────────────────
    public Doctor() { }

    public Doctor(String name,
                  String email,
                  String specialty,
                  String passwordHash,
                  List<String> availableTimes) {
        this.name           = name;
        this.email          = email;
        this.specialty      = specialty;
        this.passwordHash   = passwordHash;
        this.availableTimes = availableTimes;
    }

    // ──────────────────────────────────────────────────────────────────────────────
    // Getters & Setters
    // ──────────────────────────────────────────────────────────────────────────────
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public List<String> getAvailableTimes() { return availableTimes; }
    public void setAvailableTimes(List<String> availableTimes) { this.availableTimes = availableTimes; }

    // ──────────────────────────────────────────────────────────────────────────────
    // Utility
    // ──────────────────────────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Doctor{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", specialty='" + specialty + '\'' +
               ", availableTimes=" + availableTimes +
               '}';
    }
}
