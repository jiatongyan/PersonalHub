package com.dylan.personalhub.mapper;

import com.dylan.personalhub.entity.Person;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PersonMapper {


    Person findFirst();

    void insert(Person person);

    void update(Person person);

}