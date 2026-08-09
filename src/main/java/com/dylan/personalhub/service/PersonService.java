package com.dylan.personalhub.service;


import com.dylan.personalhub.entity.Person;
import com.dylan.personalhub.mapper.PersonMapper;
import org.springframework.stereotype.Service;


@Service
public class PersonService {


    private final PersonMapper personMapper;


    public PersonService(PersonMapper personMapper){

        this.personMapper = personMapper;

    }



    public Person getPerson(){

        return personMapper.findFirst();

    }

    public void save(Person person){

        personMapper.insert(person);

    }

    public void update(Person person){

        personMapper.update(person);

    }

}