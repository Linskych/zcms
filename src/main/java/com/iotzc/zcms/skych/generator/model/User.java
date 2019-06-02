package com.iotzc.zcms.skych.generator.model;

import lombok.Data;

/**
 * desc: 
 * table: tbl_user
 *
 * @mbg.generated Sat Jun 01 09:31:14 CST 2019
 */
@Data
public class User {

    /**
     * desc: 主键id column: id
     * @mbg.generated
     */
    private Integer id;
    /**
     * desc: 昵称 column: nick_name
     * @mbg.generated
     */
    private String nickName;
    /**
     * desc: 姓名 column: real_name
     * @mbg.generated
     */
    private String realName;
    /**
     * desc: 电话号码 column: phone
     * @mbg.generated
     */
    private String phone;
    /**
     * desc: 密码 column: password
     * @mbg.generated
     */
    private String password;
    /**
     * desc: 盐值 column: salt
     * @mbg.generated
     */
    private String salt;
    /**
     * desc: 微信openid column: open_id
     * @mbg.generated
     */
    private String openId;
    /**
     * desc: 用户状态，0正常，1失效 column: status
     * @mbg.generated
     */
    private Integer status;

}