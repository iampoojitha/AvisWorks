package com.luv2code.springcoredemo.common;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {
    public BaseballCoach(){
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }
    public String getDailyWorkout(){
        return "Do Hard BaseBall Man ;!";
    }
}
