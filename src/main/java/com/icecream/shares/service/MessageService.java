package com.icecream.shares.service;

import com.aliyuncs.exceptions.ClientException;

public interface MessageService {
    void sendCode(String phone, String code) throws ClientException;
}
