package com.dylan.personalhub.mapper;


import com.dylan.personalhub.entity.Project;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface ProjectMapper {

    List<Project> findAll();

}