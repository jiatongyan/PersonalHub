package com.dylan.personalhub.service;


import com.dylan.personalhub.entity.Education;
import com.dylan.personalhub.mapper.EducationMapper;
import org.springframework.stereotype.Service;


import java.util.List;



@Service
public class EducationService {


    private final EducationMapper educationMapper;


    public EducationService(EducationMapper educationMapper){

        this.educationMapper = educationMapper;

    }



    public List<Education> getAll(){

        return educationMapper.findAll();

    }


}