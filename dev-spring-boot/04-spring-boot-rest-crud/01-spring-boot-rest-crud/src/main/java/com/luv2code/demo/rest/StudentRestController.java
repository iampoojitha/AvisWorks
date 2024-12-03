package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import com.luv2code.demo.entity.StudentErrorResponse;
import com.luv2code.demo.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentRestController {
    List<Student> allStudents = new ArrayList<>();

    @PostConstruct
    public void startup(){
        allStudents.add(new Student("Ramesh","M"));
        allStudents.add(new Student("Kampilest","M"));
        allStudents.add(new Student("Rameshbabu","M"));
    }

    @GetMapping("/all")
    public List<Student> getAllStu(){
        return allStudents;
    }

    @GetMapping("/{studentId}")
    public Student getStuById(@PathVariable int studentId){
        if(studentId <0 || studentId >allStudents.size()){
            System.out.println("Trapped");
            throw new StudentNotFoundException("Provided Id is not valid..");
        }
        return allStudents.get(studentId);
    }

    // Moved to GlobalStudentException for Global use cases
    //    @ExceptionHandler
    //    public ResponseEntity<StudentErrorResponse> onlyHandleStudentNotFoundException(StudentNotFoundException ex){
    //        StudentErrorResponse erroredStudentResponse = new StudentErrorResponse();
    //        erroredStudentResponse.setErrorCode(HttpStatus.NOT_FOUND.name());
    //        erroredStudentResponse.setTimeStamp(System.currentTimeMillis());
    //        return new ResponseEntity<StudentErrorResponse>(erroredStudentResponse,HttpStatus.NOT_FOUND);
    //    }
    //
    //    @ExceptionHandler
    //    public ResponseEntity<StudentErrorResponse> alllExceptionsHandler(Exception ex){
    //        StudentErrorResponse erroredStudentErrorResponse = new StudentErrorResponse();
    //        erroredStudentErrorResponse.setTimeStamp(System.currentTimeMillis());
    //        erroredStudentErrorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
    //        return new ResponseEntity<StudentErrorResponse>(erroredStudentErrorResponse,HttpStatus.BAD_REQUEST);
    //    }

}
