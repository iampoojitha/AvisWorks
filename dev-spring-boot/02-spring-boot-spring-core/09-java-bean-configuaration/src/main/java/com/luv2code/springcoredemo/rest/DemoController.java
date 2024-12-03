package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define private field for the dependency

    private Coach myCoach;


    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach theCoach){
        System.out.println("In Constructor: "+getClass().getSimpleName());
        myCoach = theCoach;
    }

    @GetMapping("/workout")
    public String getWorkoutDetails(){
        return  myCoach.getDailyWorkout();
    }
}
