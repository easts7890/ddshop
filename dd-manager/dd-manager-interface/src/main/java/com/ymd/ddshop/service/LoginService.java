package com.ymd.ddshop.service;

import com.ymd.ddshop.common.dto.MessageResult;

public interface LoginService {
    MessageResult userLogin(String username,String password);
}
