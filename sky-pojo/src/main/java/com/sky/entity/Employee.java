package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
    // 主键
    private static final long serialVersionUID = 1L;
    // 主键
    private Long id;
    // 用户名
    private String username;
    // 姓名
    private String name;
    // 密码(md5加密)
    private String password;
    // 手机号
    private String phone;
    // 性别 1 male，0 female
    private String sex;
    // 身份证号
    private String idNumber;
    // 状态 0 禁用，1 正常
    private Integer status;
    // 创建时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 更新时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    // 创建人
    private Long createUser;
    // 修改人
    private Long updateUser;

}
