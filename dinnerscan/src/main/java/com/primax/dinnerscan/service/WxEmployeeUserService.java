package com.primax.dinnerscan.service;

import com.primax.dinnerscan.mapper.WxEmployeeUserMapper;
import com.primax.dinnerscan.model.WxEmployeeUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 员工service
 */
@Service
public class WxEmployeeUserService {

    private static final Logger logger = LoggerFactory.getLogger(WxEmployeeUserService.class);

    @Resource
    private WxEmployeeUserMapper weUserMapper;


    /**
     * 东聚获取授权数据
     *
     * @param userId
     * @return
     */
    public WxEmployeeUser  getWxEmployeeUserByPmx(String userId) {
        WxEmployeeUser wxEmployeeUser=new WxEmployeeUser();

        wxEmployeeUser =weUserMapper.selectPmxOne(userId);

        return wxEmployeeUser;
    }


    /**
     * 蒂芬妮获取授权数据
     *
     * @param userId
     * @return
     */
    public WxEmployeeUser  getWxEmployeeUserByTym(String userId) {
        WxEmployeeUser wxEmployeeUser=new WxEmployeeUser();

        wxEmployeeUser =weUserMapper.selectTymOne(userId);

        return wxEmployeeUser;
    }

}
