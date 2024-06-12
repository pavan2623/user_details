package com.learnspring.userdetailsapi.Interviews.service;



import com.learnspring.userdetailsapi.Interviews.dto.InterviewDto;
import com.learnspring.userdetailsapi.Interviews.exceptions.InterviewNotFoundException;
import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import com.learnspring.userdetailsapi.Interviews.repository.InterviewRepository;
import org.apache.poi.ss.usermodel.Row;
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
    public interviewInfo createInterviewInfoDetails(InterviewDto interviewDto) {
        interviewInfo interviewInfo = new interviewInfo();
        interviewInfo.setRecruiterName(interviewDto.recruiterName());
        interviewInfo.setRound(interviewDto.round());
        interviewInfo.setInterviewDate(interviewDto.interviewDate());
        interviewInfo.setTime(String.valueOf(interviewDto.time()));
        interviewInfo.setMode(interviewDto.mode());
        interviewInfo.setConsultantName(interviewDto.consultantName());
        interviewInfo.setOwnSupport(interviewDto.ownSupport());
        interviewInfo.setTechnology(interviewDto.technology());
        interviewInfo.setJobTitle(interviewDto.jobTitle());
        interviewInfo.setClientType(interviewDto.clientType());
        interviewInfo.setClientName(interviewDto.clientName());
        interviewInfo.setLocation(interviewDto.location());
        interviewInfo.setRate(String.valueOf(interviewDto.rate()));
        interviewInfo.setVendor(interviewDto.vendor());
        interviewInfo.setFeedback(interviewDto.feedback());
        interviewInfo.setComments(interviewDto.comments());

        return interviewRepository.save(interviewInfo);
    }

    @Override
    public void updateInterviewDetails(Long id, interviewInfo interviewInfo) throws InterviewNotFoundException {
        interviewInfo existingInterview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewNotFoundException("Interview not found with id: " + id));


        existingInterview.setRecruiterName(interviewInfo.getRecruiterName());
        existingInterview.setRound(interviewInfo.getRound());
        existingInterview.setInterviewDate(interviewInfo.getInterviewDate());
        existingInterview.setTime(interviewInfo.getTime());
        existingInterview.setMode(interviewInfo.getMode());
        existingInterview.setConsultantName(interviewInfo.getConsultantName());
        existingInterview.setOwnSupport(interviewInfo.getOwnSupport());
        existingInterview.setTechnology(interviewInfo.getTechnology());
        existingInterview.setJobTitle(interviewInfo.getJobTitle());
        existingInterview.setClientType(interviewInfo.getClientType());
        existingInterview.setClientName(interviewInfo.getClientName());
        existingInterview.setLocation(interviewInfo.getLocation());
        existingInterview.setRate(interviewInfo.getRate());
        existingInterview.setVendor(interviewInfo.getVendor());
        existingInterview.setFeedback(interviewInfo.getFeedback());
        existingInterview.setComments(interviewInfo.getComments());

        interviewRepository.save(existingInterview);
    }

    @Override
    public Optional<List<interviewInfo>> getInterviewDetails() {
        return Optional.of(interviewRepository.findAll());
    }

    @Override
    public Optional<interviewInfo> getInterviewDetailsByID(Long id) {
        return interviewRepository.findById(id);
    }

    @Override
    public void deleteInterviewInfoById(long id) {
        interviewRepository.deleteById(id);
    }

    @Override
    public void deleteAllInterviewInfo() {
        interviewRepository.deleteAll();
    }
}
