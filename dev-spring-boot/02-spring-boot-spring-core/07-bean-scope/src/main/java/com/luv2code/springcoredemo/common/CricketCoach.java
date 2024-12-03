package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // Changing the Scope to PROTOTYPE - Which uses Diff bean for each instance
public class CricketCoach implements Coach {
    public CricketCoach(){
        System.out.println("In Constructor: "+getClass().getSimpleName());
    }
    public String getDailyWorkout(){
        return "Do Hard Bowling Man!";
    }
}
