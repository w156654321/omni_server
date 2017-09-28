package com.dubbo.service.impl;

import com.dubbo.pojo.UUserExample;
import com.dubbo.mapper.UUserMapper;
import com.dubbo.pojo.UUser;
import com.dubbo.redis.JedisClient;
import com.dubbo.redis.JedisClusterClient;
import com.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liudh on 2017/5/28.
 */
@Transactional
@Service
@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private JedisClusterClient jedisClient;
    @Autowired
    private UUserMapper userMapper;

    public UUser getUserByName(String userName) {
//        jedisClient.set("111","222");
        UUserExample example = new UUserExample();
        UUserExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(userName);
        List<UUser> user = userMapper.selectByExample(example);
        if(!user.isEmpty())
        return user.get(0);
        return null;
    }
}
