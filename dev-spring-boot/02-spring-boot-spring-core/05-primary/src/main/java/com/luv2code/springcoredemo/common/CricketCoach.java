package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("SRH")
public class CricketCoach implements Coach {
    public String getDailyWorkout(){
        return "Do Hard Bowling Man ;!";
    }
}
