package com.ymd.ddshop.service;

import com.ymd.ddshop.common.dto.MessageResult;

public interface TokenService {
    MessageResult getUserByToken(String token);
}
