package com.iotzc.zcms.skych.generator.dao;

import com.iotzc.zcms.skych.generator.model.User;
import java.util.List;

public interface UserDao {

    /**
     * This method was generated by MyBatis Generator.
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * @mbg.generated
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * @mbg.generated
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * @mbg.generated
     */
    List<User> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * @mbg.generated
     */
    int updateByPrimaryKey(User record);
}