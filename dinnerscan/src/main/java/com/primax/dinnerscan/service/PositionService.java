package com.primax.dinnerscan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.primax.dinnerscan.mapper.PositionMapper;
import com.primax.dinnerscan.model.DssCodePosition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PositionService {
    @Resource
    private PositionMapper positionMapper;

    public DssCodePosition getPosition(String qrCode) {

        DssCodePosition position = positionMapper.selectOne(
                new QueryWrapper<DssCodePosition>()
                        .eq("QRcode", qrCode)
        );
        return position;
    }
}
