package com.dubbo.service;

import com.dubbo.pojo.UUser;

/**
 * Created by liudh on 2017/5/28.
 */
public interface UserService {

    UUser getUserByName(String userName);

}
