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

    public Education getById(Long id){

        return educationMapper.findById(id);

    }

    public void save(Education education){

        educationMapper.insert(education);

    }

    public void update(Education education){

        educationMapper.update(education);

    }

    public void delete(Long id){

        educationMapper.deleteById(id);

    }


}