package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.result.Result;
import com.sky.vo.UserLoginVO;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 */
public interface UserService {
    /**
     * 微信登录
     * @param userLoginDTO 登录信息
     * @return 用户信息
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
