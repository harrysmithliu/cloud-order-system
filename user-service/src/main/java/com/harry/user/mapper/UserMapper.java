package com.harry.user.mapper;

import com.harry.user.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    User findByUsername(String loginId);

    User findByPhone(String loginId);

    User findByEmail(String loginId);
}
