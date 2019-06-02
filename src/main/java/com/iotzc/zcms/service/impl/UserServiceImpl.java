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
    public User getByPhone(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return null;
        }
        User user = new User();
        user.setPhone(phone);
        return userDao.select(user);
    }
    
    @Override
    public void saveUser(User user) {
        userDao.insert(user);
    }
}
