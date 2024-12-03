package com.mymca.gov.in.form.controller;

import com.mymca.gov.in.form.entity.Inc9Form;
import com.mymca.gov.in.form.service.Inc9FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inc9")
public class Inc9FormController {
    private Inc9FormService inc9FormService;

    @Autowired
    public Inc9FormController(Inc9FormService inc9FormService){
        this.inc9FormService = inc9FormService;
    }

    // Create or Update an INC-9 form
    @PostMapping
    public ResponseEntity<Inc9Form> saveInc9Form(@RequestBody Inc9Form form){
        Inc9Form savedForm = inc9FormService.saveInc9Form(form);
        return new ResponseEntity<>(savedForm,HttpStatus.CREATED);
    }

    // Get all INC-9 forms
    @GetMapping
    public ResponseEntity<List<Inc9Form>> getAllInc9Forms() {
        List<Inc9Form> forms = inc9FormService.getAllInc9Forms();
        return new ResponseEntity<>(forms, HttpStatus.OK);
    }

    // Get a single INC-9 form by ID
    @GetMapping("/{id}")
    public ResponseEntity<Inc9Form> getFormById(@PathVariable Long id){
        Optional<Inc9Form> form = inc9FormService.getInc9FormById(id);
        return form.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete an INC-9 form by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInc9FormById(@PathVariable Long id) {
        inc9FormService.deleteInc9FormById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
