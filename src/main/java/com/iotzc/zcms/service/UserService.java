package com.iotzc.zcms.service;

import com.iotzc.zcms.model.User;

public interface UserService {

    User getByPhone(String phone);
    
    void saveUser(User user);
}
