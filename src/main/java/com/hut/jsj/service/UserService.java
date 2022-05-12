package com.hut.jsj.service;

import com.hut.jsj.dto.UserLogin;
import com.hut.jsj.vo.User;

public interface UserService {
    public User findByidcard(UserLogin userLogin, String ipAddr);

    public int updatewrite(String idcard);
}
