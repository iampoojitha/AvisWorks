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

    // define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In my doMyStartupStuff() ," + getClass().getSimpleName());
    }

    //define out destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In my doMyCleanupStuff() ," + getClass().getSimpleName());
    }

}