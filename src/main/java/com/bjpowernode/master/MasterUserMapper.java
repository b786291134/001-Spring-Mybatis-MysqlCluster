package com.bjpowernode.master;

import com.bjpowernode.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MasterUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();
}