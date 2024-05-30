package com.learnspring.userdetailsapi.Interviews.model;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "interviews")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class interviewInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "INTERVIEW_ID")
    private long id;

    @Column(name = "CANDIDATE_NAME")
    private String candidateName;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "INTERVIEW_DATE")
    private LocalDate interviewDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "INTERVIEWER_NAME")
    private String interviewerName;

    @Column(name = "LOCATION")
    private String location;

    @Column(name = "FEEDBACK")
    private String feedback;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "COMMENTS")
    private String comments;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;
}

