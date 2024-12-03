package com.luv2code.springboot.demo.mycoolapp.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TennisCoach implements Coach
{
    @Override
    public String getWorkoutDetails(){
        return "Bowling Bowling Bowling...!";
    }
}

