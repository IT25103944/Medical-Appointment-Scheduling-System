package com.sliit.Medical.Appointment.Scheduling.System.dto;
import com.sliit.Medical.Appointment.Scheduling.System.entity.MedicationItem;
import java.util.List;

public class PrescriptionDTO {
    private Long id;
    private String patientName;
    private String doctorName;
    private String doctorDepartment;
    private String prescriptionDate;
    private String prescriptionTime;
    private String diagnosis;
    private String notes;
    private List<MedicationItem> medications;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPatientName() { return patientName; } public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getDoctorName() { return doctorName; } public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public String getDoctorDepartment() { return doctorDepartment; } public void setDoctorDepartment(String doctorDepartment) { this.doctorDepartment = doctorDepartment; }
    public String getPrescriptionDate() { return prescriptionDate; } public void setPrescriptionDate(String prescriptionDate) { this.prescriptionDate = prescriptionDate; }
    public String getPrescriptionTime() { return prescriptionTime; } public void setPrescriptionTime(String prescriptionTime) { this.prescriptionTime = prescriptionTime; }
    public String getDiagnosis() { return diagnosis; } public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getNotes() { return notes; } public void setNotes(String notes) { this.notes = notes; }
    public List<MedicationItem> getMedications() { return medications; } public void setMedications(List<MedicationItem> medications) { this.medications = medications; }
}