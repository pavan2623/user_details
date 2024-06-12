package com.learnspring.userdetailsapi.Interviews.service;


import com.learnspring.userdetailsapi.Interviews.dto.InterviewDto;
import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface InterviewService {
    void createInterviewDetails(MultipartFile file) throws Exception;

    interviewInfo createInterviewInfoDetails(@Valid InterviewDto interviewDto);

    void updateInterviewDetails(Long id, interviewInfo interviewInfo) throws InterviewNotFoundException;

    Optional<List<interviewInfo>> getInterviewDetails();

    Optional<interviewInfo> getInterviewDetailsByID(Long id);

    void deleteInterviewInfoById(long id);

    void deleteAllInterviewInfo();
}
