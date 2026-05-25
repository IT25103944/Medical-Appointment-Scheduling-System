package com.sliit.Medical.Appointment.Scheduling.System.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    // CRITICAL FIX: "position" is an SQL keyword. We map it to "job_role" to stop crashes.
    @Column(name = "job_role", nullable = false)
    private String position;

    @Column(nullable = false , unique = true)
    private String contactNumber;

    @Column(nullable = false, length = 1000)
    private String bio;

    @Column(nullable = false)
    private Double consultationFee = 0.0;

    @Transient
    private String password;

    public Doctor() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(Double consultationFee) { this.consultationFee = consultationFee; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
