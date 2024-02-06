package org.formation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.java.Log;

@RestController
@Log
public class Endpoints {
    
    @GetMapping("/authenticated")
    public ResponseDto authenticated() {
    	log.info("Hit authenticated");
        return new ResponseDto("You are authenticated");
    }

    @GetMapping("/admin")
    public ResponseDto admin() {
    	log.info("Hit admin");
        return new ResponseDto("You are authenticated");
    }
}
