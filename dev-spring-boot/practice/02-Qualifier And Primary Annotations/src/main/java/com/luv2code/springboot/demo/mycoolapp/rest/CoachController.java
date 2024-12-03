package com.luv2code.springboot.demo.mycoolapp.rest;

import com.luv2code.springboot.demo.mycoolapp.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {
    private Coach myCoach;
    private Coach myCoach1;

    //Constructor Injection
    @Autowired
     public CoachController(@Qualifier("cricketCoach") Coach theCoach){
        myCoach = theCoach;
    }

//    // Setter injection
//    @Autowired
//    public void setMyCoach1(Coach theCoach){
//        myCoach1= theCoach;
//    }
//
//    //Field Injection
//    @Autowired
//    private Coach myCoach2;

    @GetMapping("/workout")
    String getWorkoutDetails(){
        return myCoach.getWorkoutDetails();
        //return myCoach1.getWorkoutDetails();
//        return myCoach2.getWorkoutDetails();
    }

}
