package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class CricketCoach implements Coach{
    public String getDailyWorkout(){
        return "Do Hard Bowling!";
    }
}
