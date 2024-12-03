package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In Constructor: " + getClass().getSimpleName());
    }

    public String getDailyWorkout() {
        return "Do Hard Bowling Man!";
    }

}