package com.primax.dinnerscan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primax.dinnerscan.model.DssSysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper extends BaseMapper<DssSysUser> {
}
