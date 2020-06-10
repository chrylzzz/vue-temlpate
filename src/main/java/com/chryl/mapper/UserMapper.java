package com.chryl.mapper;

import com.chryl.po.ChrUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chr.yl on 2020/6/7.
 *
 * @author Chr.yl
 */
public interface UserMapper {
    ChrUser selectUserByUName(@Param("uName") String uName);

    List<ChrUser> getAllUsers();

}
