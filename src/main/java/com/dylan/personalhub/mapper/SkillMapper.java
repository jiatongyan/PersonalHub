package com.dylan.personalhub.mapper;

import com.dylan.personalhub.entity.Skill;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


@Mapper
public interface SkillMapper {

    List<Skill> findAll();

    Skill findById(Long id);

    void insert(Skill skill);

    void update(Skill skill);

    void deleteById(Long id);

}