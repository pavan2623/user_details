package com.learnspring.userdetailsapi.Interviews.controller;

import com.learnspring.userdetailsapi.Interviews.dto.InterviewDto;

import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import com.learnspring.userdetailsapi.Interviews.service.InterviewService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping(value = "/upload-interview-excel", consumes = {"multipart/form-data"})
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
        interviewService.createInterviewDetails(file);
        return new ResponseEntity<>("Excel data uploaded and inserted into database successfully.", HttpStatus.OK);
    }

    @PostMapping("/create-interview")
    public ResponseEntity<interviewInfo> createInterviewInfo(@Valid @RequestBody InterviewDto interviewDto) {
        var newInterviewInfo = interviewService.createInterviewInfoDetails(interviewDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newInterviewInfo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Interview Details")
    public ResponseEntity<String> updateInterviewInfo(@PathVariable Long id, @RequestBody interviewInfo interviewInfo) {
        interviewService.updateInterviewDetails(id, interviewInfo);
        return new ResponseEntity<>("Interview details updated successfully.", HttpStatus.OK);
    }

    @GetMapping("/fetch-interviews")
    @Operation(summary = "Fetch Interview Details")
    public ResponseEntity<List<interviewInfo>> fetchInterviewDetails() {
        Optional<List<interviewInfo>> interviews = interviewService.getInterviewDetails();

        return interviews.map(interviewDetails -> new ResponseEntity<>(interviewDetails, HttpStatus.OK))
                .orElseThrow(() -> new InterviewNotFoundException("No interviews found.."));
    }

    @GetMapping("/fetch-interview-by-ID/{id}")
    @Operation(summary = "Fetch Interview Details By ID")
    public ResponseEntity<Optional<interviewInfo>> fetchInterviewDetailsByID(@PathVariable Long id) {
        Optional<Optional<interviewInfo>> interview = Optional.ofNullable(interviewService.getInterviewDetailsByID(id));

        return interview.map(interviewDetails -> new ResponseEntity<>(interviewDetails, HttpStatus.OK))
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found with id: " + id));
    }

    @DeleteMapping("/delete-all-interviews")
    @Operation(summary = "Delete All Interviews")
    public ResponseEntity<HttpStatus> deleteAllInterviewInfo() {
        interviewService.deleteAllInterviewInfo();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete-interview-by-id/{id}")
    @Operation(summary = "Delete Interview By Id")
    public ResponseEntity<HttpStatus> deleteInterviewInfoById(@PathVariable("id") long id) {
        interviewService.deleteInterviewInfoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
