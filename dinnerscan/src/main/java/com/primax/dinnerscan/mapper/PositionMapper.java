package com.primax.dinnerscan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primax.dinnerscan.model.DssCodePosition;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PositionMapper extends BaseMapper<DssCodePosition> {
}