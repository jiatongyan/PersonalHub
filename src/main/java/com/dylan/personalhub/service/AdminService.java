package com.dylan.personalhub.service;


import com.dylan.personalhub.entity.AdminUser;
import com.dylan.personalhub.mapper.AdminUserMapper;
import org.springframework.stereotype.Service;



@Service
public class AdminService {


    private final AdminUserMapper mapper;


    public AdminService(AdminUserMapper mapper){

        this.mapper=mapper;

    }



    public boolean login(
            String username,
            String password
    ){


        AdminUser user =
                mapper.findByUsername(username);


        if(user==null){

            return false;

        }


        return user.getPassword()
                .equals(password);


    }

}