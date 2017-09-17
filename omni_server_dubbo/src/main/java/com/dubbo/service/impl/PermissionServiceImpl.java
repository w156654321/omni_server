package com.dubbo.service.impl;

import com.dubbo.mapper.UPermissionMapper;
import com.dubbo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by liudh on 2017/5/28.
 */
@Transactional
@Service
@Component("permissionServiceImpl")
public class PermissionServiceImpl  implements PermissionService {

    @Autowired
    private UPermissionMapper permissionMapper;

    public Set<String> selectPermissionByUserId(Long userId) {
        Set<String> set = permissionMapper.selectPermissionByUserId(userId);
        return set;
    }
}
