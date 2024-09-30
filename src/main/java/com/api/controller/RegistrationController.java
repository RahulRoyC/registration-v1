package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping// gets all the data from the db & convert it into JSON
    public ResponseEntity <List<RegistrationDto>>getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping//saving the data in db
    public ResponseEntity <?> createRegistration(
            //data will be copied due to @RequestBody
           @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result
    ){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

    @DeleteMapping// delete the record
    public ResponseEntity<String>deleteRegistration(
            //gives the paramater @RequestParam
            @RequestParam long id
            ){
            registrationService.deleteRegistration(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @PutMapping("/{id}")// to update the record in db via api
    public ResponseEntity <Registration> updateRegistration(
        @PathVariable long id,
        @RequestBody Registration registration
    ){
        Registration updatedRegistration= registrationService.updateRegistration(id, registration);
            return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegById(
            @PathVariable long id
    ){
        RegistrationDto dto = registrationService.getRegById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}