package com.luv2code.demo.exception;

public class StudentNotFoundException extends  RuntimeException{
    public StudentNotFoundException(String msg){
        super(msg);
    }
}
