package com.mymca.gov.in.form.service;

import com.mymca.gov.in.form.entity.Inc9Form;
import com.mymca.gov.in.form.repository.Inc9FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Inc9FormService {

    private final Inc9FormRepository inc9FormRepository;

    @Autowired
    public Inc9FormService(Inc9FormRepository inc9FormRepository){
        this.inc9FormRepository = inc9FormRepository;
    }

    // Create or update an INC-9 form
    public Inc9Form saveInc9Form(Inc9Form form){
        return inc9FormRepository.save(form);
    }

    // Retrieve all INC-9 forms
    public List<Inc9Form> getAllInc9Forms(){
        return inc9FormRepository.findAll();
    }

    // Retrieve an INC-9 form by its ID
    public Optional<Inc9Form> getInc9FormById(Long id){
        return inc9FormRepository.findById(id);
    }

    // Delete an INC-9 form by its ID
    public void deleteInc9FormById(Long id){
        inc9FormRepository.deleteById(id);
    }
}
