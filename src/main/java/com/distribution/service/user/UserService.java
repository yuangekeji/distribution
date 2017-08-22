package com.distribution.service.user;

import com.distribution.dao.user.mapper.UserMapper;
import com.distribution.dao.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingxin on 2017/8/13.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean addUser(User user) {

        if(userMapper.insertSelective(user) > 0){
            return true;
        }else{
            return false;
        }
    }

    public User findUser(){
        return  userMapper.selectByPrimaryKey(1);
    }
}
