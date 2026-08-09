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



    /**
     * 登录校验，成功返回用户对象，失败返回 null
     */
    public AdminUser login(
            String username,
            String password
    ){


        AdminUser user =
                mapper.findByUsername(username);


        if(user==null){

            return null;

        }


        if (user.getPassword().equals(password)) {
            // 不把密码带到 session 中
            user.setPassword(null);
            return user;
        }

        return null;


    }

}