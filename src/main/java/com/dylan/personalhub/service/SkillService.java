package com.dylan.personalhub.service;

import com.dylan.personalhub.entity.Skill;
import com.dylan.personalhub.mapper.SkillMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SkillService {


    private final SkillMapper skillMapper;


    public SkillService(
            SkillMapper skillMapper
    ){

        this.skillMapper = skillMapper;

    }



    public List<Skill> getAll(){

        return skillMapper.findAll();

    }

}