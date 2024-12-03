package com.luv2code.demo.exception;

import com.luv2code.demo.entity.StudentErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalStudentException {

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> onlyHandleStudentNotFoundException(StudentNotFoundException ex){
        StudentErrorResponse erroredStudentResponse = new StudentErrorResponse();
        erroredStudentResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
        erroredStudentResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentErrorResponse>(erroredStudentResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> allExceptionsHandler(Exception ex){
        StudentErrorResponse erroredStudentErrorResponse = new StudentErrorResponse();
        erroredStudentErrorResponse.setTimeStamp(System.currentTimeMillis());
        erroredStudentErrorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<StudentErrorResponse>(erroredStudentErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
