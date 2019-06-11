package com.iotzc.zcms.service;

import com.iotzc.zcms.model.User;

public interface UserService {

    User getByUserName(String userName);
    
    int saveUser(User user);
    
    int updateUser(User user);
}
