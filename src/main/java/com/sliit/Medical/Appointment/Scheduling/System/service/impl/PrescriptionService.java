package com.sliit.Medical.Appointment.Scheduling.System.service;

import com.sliit.Medical.Appointment.Scheduling.System.dto.PrescriptionDTO;
import java.util.List;

public interface PrescriptionService {
    PrescriptionDTO createPrescription(PrescriptionDTO dto);
    List<PrescriptionDTO> getAllPrescriptions();
    void deletePrescription(Long id);
}