package com.dubbo.service.impl;

import com.dubbo.pojo.UPermission;
import com.dubbo.service.TestService;
import com.dubbo.mapper.UPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liudh on 2017/5/25.
 */
@Transactional
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UPermissionMapper uPermissionMapper;

    /**
     * 测试事务
     * @return
     */
    public UPermission insertTest() {
        UPermission uPermission = uPermissionMapper.selectByPrimaryKey(4L);
        UPermission permission = new UPermission();
        permission.setName("asdasdasd");
        uPermissionMapper.insert(permission);
        throw new RuntimeException("发生了异常");
//      return uPermission;
    }
}
