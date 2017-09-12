package com.dubbo.mapper;

import java.util.List;

import com.dubbo.pojo.URolePermission;
import com.dubbo.pojo.URolePermissionExample;
import org.apache.ibatis.annotations.Param;

public interface URolePermissionMapper {

    int countByExample(URolePermissionExample example);

    int deleteByExample(URolePermissionExample example);

    int insert(URolePermission record);

    int insertSelective(URolePermission record);

    List<URolePermission> selectByExample(URolePermissionExample example);

    int updateByExampleSelective(@Param("record") URolePermission record, @Param("example") URolePermissionExample example);

    int updateByExample(@Param("record") URolePermission record, @Param("example") URolePermissionExample example);
}