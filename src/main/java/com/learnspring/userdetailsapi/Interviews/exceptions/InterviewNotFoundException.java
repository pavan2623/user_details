package com.learnspring.userdetailsapi.Interviews.exceptions;
public class InterviewNotFoundException extends RuntimeException {

    public InterviewNotFoundException(String message) {
        super(message);
    }
}
