package com.api.services;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFound;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;
    // Constructor Dependency Injection: improves readability and makes it independent and flexible and loose coupled
    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }
    public List<RegistrationDto> getRegistrations(){ //for getting all the details from the database we have to create a method
        List<Registration> registrations = registrationRepository.findAll();// there are may be many data in the db thats why we used the list
        List<RegistrationDto> dtos = registrations.stream().map(r -> maptoDto(r)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        //copy dto to entity

        Registration registration =mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);

        //copy entity to dto
        RegistrationDto dto = maptoDto(savedEntity);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setId(id);
        r.setName(registration.getName());
        r.setEmail(registration.getEmail());
        r.setMobile(registration.getMobile());
        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }

    public Registration mapToEntity(RegistrationDto registrationDto){
        Registration registration = new Registration();
        registration.setName(registrationDto.getName());
        registration.setEmail(registrationDto.getEmail());
        registration.setMobile(registrationDto.getMobile());
        return registration;
    }

    public RegistrationDto maptoDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }

    public RegistrationDto getRegById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Record not Found")
        );
        return maptoDto(registration);
    }
}