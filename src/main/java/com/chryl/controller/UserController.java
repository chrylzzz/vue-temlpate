package com.chryl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chryl.core.exception.ResponseException;
import com.chryl.core.response.ReturnResult;
import com.chryl.po.ChrRole;
import com.chryl.po.ChrUser;
import com.chryl.service.RoleService;
import com.chryl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PipedReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * vue-admin 接口
 * mysql 连接,实现前端控制权限
 * <p>
 * Created by Chryl on 2019/12/31.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //验证用户信息
    @PostMapping("/login")
    public Object validateLogin(@RequestBody ChrUser chrUser, HttpServletResponse response) throws ResponseException {
//        String token = "9888a28586104f57970ec7c5666989a6";
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        Map<String, Object> resMap = new HashMap<>();

        ChrUser chrUser1 = userService.selectUserByUName(chrUser.getUsername(), chrUser.getPassword());
        //redis存储用户信息
        String userJsonStr = JSON.toJSONString(chrUser1);
//        stringRedisTemplate.opsForValue().set(token, userJsonStr, 7, TimeUnit.DAYS);//模拟redis
        resMap.put("token", token);
        resMap.put("code", "200");
        resMap.put("status", "success");
        response.setHeader("my-Header", "123");
        return ReturnResult.create(resMap);
    }

    @GetMapping("/info")
    public Object getInfo(String token) throws ResponseException {
        ChrUser chrUser = userService.selectUserByUName("admin", "111111");//模拟redis
//        ChrUser chrUser = JSON.parseObject(stringRedisTemplate.opsForValue().get(token), ChrUser.class);
        ChrRole chrRole = roleService.getRoleInfoByUserId(chrUser.getId());
        if (chrRole == null) {
            return ReturnResult.create(500, "gogogo");
        }
        List<String> roles = new ArrayList<>();
        roles.add(chrRole.getCode());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", chrUser);
        jsonObject.put("roles", roles);
        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put("user", chrUser);
        loginMap.put("roles", roles);
        return ReturnResult.create(loginMap);
    }

    @GetMapping("/getAllUser")
    public Object getAllUser(String token) throws ResponseException {
        if (StringUtils.isBlank(token)) {
            return ReturnResult.create(null);
        }
        List<ChrUser> userList = userService.getAllUsers();
        return ReturnResult.create(userList);
    }

    @PostMapping("/logout")
    public ReturnResult logout(String token) {
        //删除 redis token
        if (StringUtils.isBlank(token)) {
            return ReturnResult.create(null);
        }
        Boolean delete = stringRedisTemplate.delete(token);
        if (delete) {
            return ReturnResult.create(HttpStatus.OK);
        } else {
            return ReturnResult.create(null);
        }
    }

}
