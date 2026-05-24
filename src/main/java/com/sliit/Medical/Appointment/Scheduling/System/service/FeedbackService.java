package com.sliit.Medical.Appointment.Scheduling.System.service;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback submitFeedback(Feedback feedback);
    List<Feedback> getAllFeedbacks();
    List<Feedback> getApprovedFeedbacksForDoctor(Long doctorId);
    Feedback updateFeedbackStatus(Long id, String status);
    void deleteFeedback(Long id);
}