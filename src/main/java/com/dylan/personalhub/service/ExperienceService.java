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

    public Experience getById(Long id){

        return experienceMapper.findById(id);

    }

    public void save(Experience experience){

        experienceMapper.insert(experience);

    }

    public void update(Experience experience){

        experienceMapper.update(experience);

    }

    public void delete(Long id){

        experienceMapper.deleteById(id);

    }


}