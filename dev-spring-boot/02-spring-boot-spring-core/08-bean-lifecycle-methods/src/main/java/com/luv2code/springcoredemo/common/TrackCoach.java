package com.luv2code.springcoredemo.common;

import jdk.jfr.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements  Coach{
    public TrackCoach(){
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }
    public String getDailyWorkout(){
        return "Do Hard Track of 5k Man!";
    }
}
