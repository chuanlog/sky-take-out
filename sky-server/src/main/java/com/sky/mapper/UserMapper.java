package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @param openid openid
     * @return User
     */
    User getByOpenid(String openid);

    /**
     * 新增用户
     * @param user user
     */
    void insert(User user);
}
