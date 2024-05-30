package com.learnspring.userdetailsapi.Interviews.service;



import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import com.learnspring.userdetailsapi.Interviews.repository.InterviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final InterviewExcelReaderService interviewExcelReaderService;

    public InterviewServiceImpl(InterviewRepository interviewRepository, InterviewExcelReaderService interviewExcelReaderService) {
        this.interviewRepository = interviewRepository;
        this.interviewExcelReaderService = interviewExcelReaderService;
    }

    @Override
    public void createInterviewDetails(MultipartFile file) throws Exception {
        List<interviewInfo> interviewInfoList = interviewExcelReaderService.readExcelFile(file);
        interviewRepository.saveAll(interviewInfoList);
    }

    @Override
    public void updateInterviewDetails(Long id, interviewInfo interviewInfo) throws InterviewNotFoundException {
        interviewInfo existingInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found with id: " + id));

        // Update fields
        existingInterview.setCandidateName(interviewInfo.getCandidateName());
        existingInterview.setPosition(interviewInfo.getPosition());
        existingInterview.setInterviewDate(interviewInfo.getInterviewDate());
        existingInterview.setStatus(interviewInfo.getStatus());
        existingInterview.setInterviewerName(interviewInfo.getInterviewerName());
        existingInterview.setLocation(interviewInfo.getLocation());
        existingInterview.setFeedback(interviewInfo.getFeedback());
        existingInterview.setRating(interviewInfo.getRating());
        existingInterview.setComments(interviewInfo.getComments());

        interviewRepository.save(existingInterview);
    }

    @Override
    public Optional<List<interviewInfo>> getInterviewDetails() {
        return Optional.of(interviewRepository.findAll());
    }
}
