package com.chryl.service;

import com.chryl.core.exception.EnumError;
import com.chryl.core.exception.ResponseException;
import com.chryl.mapper.UserMapper;
import com.chryl.po.ChrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chr.yl on 2020/6/7.
 *
 * @author Chr.yl
 */
@Service
public class UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;


    public ChrUser selectUserByUName(String uName, String pass) throws ResponseException {
        ChrUser chrUser = userMapper.selectUserByUName(uName);
        if (chrUser == null || !pass.equals(chrUser.getPassword())) {
            throw new ResponseException(EnumError.USER_LOGIN_FAIL);
        }
        return chrUser;
    }

    public List<ChrUser> getAllUsers() throws ResponseException {
        List<ChrUser> chrUserList = userMapper.getAllUsers();
        if (chrUserList == null || chrUserList.size() < 0) {
            throw new ResponseException(EnumError.USER_NOT_EXISTS);
        }
        return chrUserList;
    }
}
