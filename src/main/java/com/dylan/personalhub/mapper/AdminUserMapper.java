package com.dylan.personalhub.mapper;


import com.dylan.personalhub.entity.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface AdminUserMapper {


    @Select("""
        SELECT *
        FROM admin_user
        WHERE username=#{username}
    """)
    AdminUser findByUsername(String username);


}