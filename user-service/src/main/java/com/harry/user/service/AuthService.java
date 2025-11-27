package com.harry.user.service;

import com.harry.user.model.po.User;

public interface AuthService {

    /**
     * Login with username / phone / email + password
     */
    User login(String loginId, String password);
}
