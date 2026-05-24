package com.sliit.Medical.Appointment.Scheduling.System.dto;

public class FeedbackDTO {
    private Long id;
    private String patientName;
    private String doctorName;
    private int rating;
    private String comments;
    private String submissionDate;
    private String status;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPatientName() { return patientName; } public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getDoctorName() { return doctorName; } public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    public int getRating() { return rating; } public void setRating(int rating) { this.rating = rating; }
    public String getComments() { return comments; } public void setComments(String comments) { this.comments = comments; }
    public String getSubmissionDate() { return submissionDate; } public void setSubmissionDate(String submissionDate) { this.submissionDate = submissionDate; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
}