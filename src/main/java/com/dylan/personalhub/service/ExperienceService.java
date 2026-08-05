package com.dylan.personalhub.service;


import com.dylan.personalhub.entity.Experience;
import com.dylan.personalhub.mapper.ExperienceMapper;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class ExperienceService {


    private final ExperienceMapper experienceMapper;


    public ExperienceService(
            ExperienceMapper experienceMapper
    ){

        this.experienceMapper = experienceMapper;

    }



    public List<Experience> getAll(){

        return experienceMapper.findAll();

    }


}