package com.learnspring.userdetailsapi.Interviews.service;



import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    void createInterviewDetails(MultipartFile file) throws Exception;

    void updateInterviewDetails(Long id, interviewInfo interviewInfo) throws InterviewNotFoundException;

    Optional<List<interviewInfo>> getInterviewDetails();
}

