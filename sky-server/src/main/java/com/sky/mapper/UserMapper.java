package com.sky.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
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
    @DS("slave")
    User getByOpenid(String openid);

    /**
     * 新增用户
     * @param user user
     */
    @DS("master")
    void insert(User user);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @DS("slave")
    User getById(Long userId);

    /**
     * 根据统计某一天结束前的总用户数量
     *
     * @param map
     * @return
     */
    @DS("slave")
    Integer countByMap(Map map);
}
