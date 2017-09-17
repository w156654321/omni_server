package com.dubbo.service.impl;

import com.dubbo.mapper.URoleMapper;
import com.dubbo.service.RoleService;
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
@Component("roleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private URoleMapper roleMapper;

    public Set<String> selectRoleByUserId(Long userId) {
        Set<String> set = roleMapper.selectRoleByUserId(userId);
        return set;
    }
}
