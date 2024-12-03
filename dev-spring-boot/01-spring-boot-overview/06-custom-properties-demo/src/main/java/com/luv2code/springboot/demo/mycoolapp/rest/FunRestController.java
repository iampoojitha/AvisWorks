package com.luv2code.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    //custom properties

    @Value("${coach.name}")
    private String coach_name;

    @Value("${team}")
    private String team;


    // expose a new endpoint for "/teaminfo"

    @GetMapping("/teaminfo")
    public String getTeamInfo(){
        return coach_name + " " + team;
    }

    // expose a new endpoint for "/"

    @GetMapping("/")
    public String sayHello(){
        return "Hello World!";
    }

    //expose a new endpoint for "/workout"

    @GetMapping("/workout")
    public String getWorkout(){
        return "Run a hard 5k";
    }

    // expose a new endpoint for "/fortune"
    @GetMapping("/fortune")
    public String getDailyFortune(){
        return "Today is your Best Day!";
    }
}
