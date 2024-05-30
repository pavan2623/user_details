package com.learnspring.userdetailsapi.Interviews.controller;


import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import com.learnspring.userdetailsapi.Interviews.service.InterviewService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/interviews")
public class InterviewsController {
    private final InterviewService interviewService;

    public InterviewsController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping(value = "/upload-excel", consumes = {"multipart/form-data"})
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            interviewService.createInterviewDetails(file);
            return "Excel data uploaded and inserted into database successfully.";
        } catch (Exception e) {
            return "Error uploading Excel data: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Interview Details")
    public ResponseEntity<String> updateInterview(@PathVariable Long id, @RequestBody interviewInfo interviewInfo) {
        try {
            interviewService.updateInterviewDetails(id, interviewInfo);
            return new ResponseEntity<>("Interview details updated successfully.", HttpStatus.OK);
        } catch (InterviewNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating interview details: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fetch-interviews")
    @Operation(summary = "Fetch Interview Details")
    public ResponseEntity<List<interviewInfo>> fetchInterviewDetails() {
        Optional<List<interviewInfo>> interviews = interviewService.getInterviewDetails();

        return interviews.map(interviewDetails -> new ResponseEntity<>(interviewDetails, HttpStatus.OK))
                .orElseThrow(() -> new InterviewNotFoundException("No interviews found."));
    }
}
