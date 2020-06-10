package com.chryl.service;

import com.chryl.core.exception.EnumError;
import com.chryl.core.exception.ResponseException;
import com.chryl.mapper.RoleMapper;
import com.chryl.po.ChrRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chr.yl on 2020/6/7.
 *
 * @author Chr.yl
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;


   public ChrRole getRoleInfoByUserId(@Param("userId") Integer userId) throws ResponseException {
        if (StringUtils.isBlank(String.valueOf(userId))) {
            throw new ResponseException(EnumError.ROLE__NOT_EXISTS);
        }
        ChrRole chrRole = roleMapper.getRoleInfoByUserId(userId);
        if (chrRole == null) {
            throw new ResponseException(EnumError.ROLE__NOT_EXISTS);
        }
        return chrRole;
    }
}
