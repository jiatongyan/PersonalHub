package com.dylan.personalhub.mapper;

import com.dylan.personalhub.entity.Education;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface EducationMapper {


    List<Education> findAll();

    Education findById(Long id);

    void insert(Education education);

    void update(Education education);

    void deleteById(Long id);

}