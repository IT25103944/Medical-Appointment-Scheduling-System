package com.sliit.Medical.Appointment.Scheduling.System.service.impl;

import com.sliit.Medical.Appointment.Scheduling.System.dto.DoctorDTO;
import com.sliit.Medical.Appointment.Scheduling.System.entity.Doctor;
import com.sliit.Medical.Appointment.Scheduling.System.entity.User;
import com.sliit.Medical.Appointment.Scheduling.System.repository.DoctorRepository;
import com.sliit.Medical.Appointment.Scheduling.System.repository.UserRepository;
import com.sliit.Medical.Appointment.Scheduling.System.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public DoctorDTO createDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setDepartment(dto.getDepartment());
        doctor.setPosition(dto.getPosition());
        doctor.setContactNumber(dto.getContactNumber());
        doctor.setBio(dto.getBio());
        doctor.setConsultationFee(dto.getConsultationFee() == null ? 0.0 : dto.getConsultationFee());

        return mapToDTO(doctorRepository.save(doctor));
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<DoctorDTO> searchDoctors(String keyword) {
        return doctorRepository.findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(keyword, keyword)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public DoctorDTO getDoctorById(Long id) {
        return mapToDTO(doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found")));
    }

    @Override
    public DoctorDTO updateDoctor(Long id, DoctorDTO dto) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setDepartment(dto.getDepartment());
        doctor.setPosition(dto.getPosition());
        doctor.setContactNumber(dto.getContactNumber());
        doctor.setBio(dto.getBio());
        doctor.setConsultationFee(dto.getConsultationFee() == null ? doctor.getConsultationFee() : dto.getConsultationFee());

        return mapToDTO(doctorRepository.save(doctor));
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    private DoctorDTO mapToDTO(Doctor doctor) {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setEmail(doctor.getEmail());
        dto.setDepartment(doctor.getDepartment());
        dto.setPosition(doctor.getPosition());
        dto.setContactNumber(doctor.getContactNumber());
        dto.setBio(doctor.getBio());
        dto.setConsultationFee(doctor.getConsultationFee());

        if (doctor.getEmail() != null) {
            Optional<User> userOptional = userRepository.findByEmail(doctor.getEmail());
            userOptional.ifPresent(user -> dto.setPassword(user.getPassword()));
        }
        return dto;
    }
}
