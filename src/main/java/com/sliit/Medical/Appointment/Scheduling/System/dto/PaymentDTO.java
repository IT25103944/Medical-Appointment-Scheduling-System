package com.sliit.Medical.Appointment.Scheduling.System.dto;

public class PaymentDTO {
    private Long id;
    private String patientEmail;
    private String patientName;
    private String description;
    private Double totalAmount;
    private Double discount;
    private Double finalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String paymentDate;
    private String paymentTime;
    private String cashierName;

    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getPatientEmail() { return patientEmail; } public void setPatientEmail(String patientEmail) { this.patientEmail = patientEmail; }
    public String getPatientName() { return patientName; } public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getDescription() { return description; } public void setDescription(String description) { this.description = description; }
    public Double getTotalAmount() { return totalAmount; } public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    public Double getDiscount() { return discount; } public void setDiscount(Double discount) { this.discount = discount; }
    public Double getFinalAmount() { return finalAmount; } public void setFinalAmount(Double finalAmount) { this.finalAmount = finalAmount; }
    public String getPaymentMethod() { return paymentMethod; } public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; } public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getPaymentDate() { return paymentDate; } public void setPaymentDate(String paymentDate) { this.paymentDate = paymentDate; }
    public String getPaymentTime() { return paymentTime; } public void setPaymentTime(String paymentTime) { this.paymentTime = paymentTime; }
    public String getCashierName() { return cashierName; } public void setCashierName(String cashierName) { this.cashierName = cashierName; }
}