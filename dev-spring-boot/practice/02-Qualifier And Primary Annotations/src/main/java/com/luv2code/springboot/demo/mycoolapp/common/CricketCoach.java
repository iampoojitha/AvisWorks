package com.luv2code.springboot.demo.mycoolapp.common;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach
{
    @Override
    public String getWorkoutDetails(){
        return "CricketCoach - Bowling Bowling Bowling...!";
    }
}
