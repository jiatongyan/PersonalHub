package com.dylan.personalhub.service;


import com.dylan.personalhub.entity.Project;
import com.dylan.personalhub.mapper.ProjectMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService {


    private final ProjectMapper projectMapper;

    public ProjectService(
            ProjectMapper projectMapper
    ){

        this.projectMapper = projectMapper;

    }

    public List<Project> getAll(){

        return projectMapper.findAll();

    }

}