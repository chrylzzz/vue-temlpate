package com.chryl.mapper;

import com.chryl.po.ChrRole;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Chr.yl on 2020/6/7.
 *
 * @author Chr.yl
 */
public interface RoleMapper {

    ChrRole getRoleInfoByUserId(@Param("userId") Integer userId);
}
