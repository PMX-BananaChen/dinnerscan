package com.primax.dinnerscan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.primax.dinnerscan.config.MD5Utils;
import com.primax.dinnerscan.mapper.LoginMapper;
import com.primax.dinnerscan.model.DssSysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private LoginMapper loginMapper;

    public DssSysUser getUser(DssSysUser dssSysUser) {

        DssSysUser user = loginMapper.selectOne(
                new QueryWrapper<DssSysUser>()
                        .eq("UserName", dssSysUser.getUserName()).eq("Password", MD5Utils.code(dssSysUser.getPassword()))
        );

        return user;
    }
}
