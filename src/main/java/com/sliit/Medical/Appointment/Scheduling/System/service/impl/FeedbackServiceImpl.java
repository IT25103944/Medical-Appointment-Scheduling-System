package com.sliit.Medical.Appointment.Scheduling.System.service.impl;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Feedback;
import com.sliit.Medical.Appointment.Scheduling.System.repository.FeedbackRepository;
import com.sliit.Medical.Appointment.Scheduling.System.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback submitFeedback(Feedback feedback) {
        feedback.setStatus("Pending");
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getApprovedFeedbacksForDoctor(Long doctorId) {
        return feedbackRepository.findByDoctorIdAndStatus(doctorId, "Approved");
    }

    @Override
    public Feedback updateFeedbackStatus(Long id, String status) {
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new RuntimeException("Feedback not found"));
        feedback.setStatus(status);
        return feedbackRepository.save(feedback);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}