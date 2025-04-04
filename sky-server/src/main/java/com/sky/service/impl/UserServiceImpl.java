package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;

    public static String WX_LOGIN="https://api.weixin.qq.com/sns/jscode2session";

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());

        // 判断是否为新用户，如果是新用户，自动完成注册
        User user = userMapper.getByOpenid(openid);
        if(user == null){
            user = User.builder().
                    openid(openid).
                    createTime(LocalDateTime.now()).
                    build();
            userMapper.insert(user);
        }

        return user;
    }

    private String getOpenid(String code) {
        HashMap<String,String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type","authorization_code");

        // 调用微信接口服务，获取微信用户信息
        String result = HttpClientUtil.doGet(WX_LOGIN,map);

        JSONObject jsonObject = JSON.parseObject(result);
        String openid= jsonObject.getString("openid");
        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        return openid;
    }
}
