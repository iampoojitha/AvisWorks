package com.luv2code.demo.entity;

public class StudentErrorResponse {
    private String errorCode;
    private Long timeStamp;

    public StudentErrorResponse(String errorCode, Long timeStamp) {
        this.errorCode = errorCode;
        this.timeStamp = timeStamp;
    }

    public StudentErrorResponse() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "StudentErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
