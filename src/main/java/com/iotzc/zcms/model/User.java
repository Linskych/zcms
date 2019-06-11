package com.iotzc.zcms.model;

import lombok.Data;

/**
 * desc: 
 * table: tbl_user
 */
@Data
public class User {

    /**
     * desc: 主键id column: id
     */
    private Integer id;
    /**
     * desc: 昵称 column: nick_name
     */
    private String nickName;
    /**
     * desc: 姓名 column: real_name
     */
    private String realName;
    /**
     * desc: 账号(电话号码) column: user_name
     */
    private String userName;
    /**
     * desc: 密码 column: password
     */
    private String password;
    /**
     * desc: 盐值 column: salt
     */
    private String salt;
    /**
     * desc: 微信openid column: open_id
     */
    private String openId;
    /**
     * desc: 用户状态，0正常，1失效 column: status
     */
    private Integer status;
    
    public String getCredentialsSalt() {
        return userName + salt;
    }

}