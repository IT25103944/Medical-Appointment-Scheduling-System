package com.sliit.Medical.Appointment.Scheduling.System.service;
import com.sliit.Medical.Appointment.Scheduling.System.dto.DoctorDTO;
import java.util.List;

public interface DoctorService {
    DoctorDTO createDoctor(DoctorDTO dto);
    List<DoctorDTO> getAllDoctors();
    List<DoctorDTO> searchDoctors(String keyword);
    DoctorDTO getDoctorById(Long id);
    DoctorDTO updateDoctor(Long id, DoctorDTO dto);
    void deleteDoctor(Long id);
}