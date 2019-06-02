package com.iotzc.zcms.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.iotzc.zcms.model.User;


@Repository
public interface UserDao {

    int deleteBy(User user);

    int insert(User user);

    User select(User user);

    List<User> selectAll(User user);

    int update(User user);
}
