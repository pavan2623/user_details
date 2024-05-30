package com.learnspring.userdetailsapi.Interviews.repository;

import com.learnspring.userdetailsapi.Interviews.model.interviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<interviewInfo, Long> {
}
