package com.iotzc.zcms.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iotzc.zcms.dao.UserDao;
import com.iotzc.zcms.model.User;
import com.iotzc.zcms.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    
    @Override
    public User getByUserName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        User user = new User();
        user.setUserName(userName);
        return userDao.select(user);
    }
    
    @Override
    public int saveUser(User user) {
        return userDao.insert(user);
    }
    
    @Override
    public int updateUser(User user) {
        return userDao.update(user);
    }
}
