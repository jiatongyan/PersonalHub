package com.dylan.personalhub.mapper;

import com.dylan.personalhub.entity.Experience;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ExperienceMapper {

    List<Experience> findAll();

    Experience findById(Long id);

    void insert(Experience experience);

    void update(Experience experience);

    void deleteById(Long id);

}