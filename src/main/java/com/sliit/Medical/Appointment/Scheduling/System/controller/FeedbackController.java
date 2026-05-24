package com.sliit.Medical.Appointment.Scheduling.System.controller;

import com.sliit.Medical.Appointment.Scheduling.System.entity.Feedback;
import com.sliit.Medical.Appointment.Scheduling.System.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestBody Feedback feedback) {
        try {
            return ResponseEntity.ok(feedbackService.submitFeedback(feedback));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }

    // Patient Dashboard fetches this to see approved reviews for a doctor
    @GetMapping("/doctor/{doctorId}/approved")
    public ResponseEntity<List<Feedback>> getApprovedReviews(@PathVariable Long doctorId) {
        return ResponseEntity.ok(feedbackService.getApprovedFeedbacksForDoctor(doctorId));
    }

    // Admin Dashboard uses this to approve a review
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            return ResponseEntity.ok(feedbackService.updateFeedbackStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        try {
            feedbackService.deleteFeedback(id);
            return ResponseEntity.ok().body("{\"message\": \"Feedback deleted successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
}