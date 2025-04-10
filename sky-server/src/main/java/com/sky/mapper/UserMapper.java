package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

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

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User getById(Long userId);

    /**
     * 根据统计某一天结束前的总用户数量
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
