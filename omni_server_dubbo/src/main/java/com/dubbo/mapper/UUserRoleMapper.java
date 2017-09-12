package com.dubbo.mapper;

import java.util.List;

import com.dubbo.pojo.UUserRole;
import com.dubbo.pojo.UUserRoleExample;
import org.apache.ibatis.annotations.Param;

public interface UUserRoleMapper {

    int countByExample(UUserRoleExample example);

    int deleteByExample(UUserRoleExample example);

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    List<UUserRole> selectByExample(UUserRoleExample example);

    int updateByExampleSelective(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int updateByExample(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

}