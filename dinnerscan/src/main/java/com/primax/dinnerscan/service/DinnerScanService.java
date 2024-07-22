package com.primax.dinnerscan.service;

import com.primax.dinnerscan.mapper.DinnerScanMapper;
import com.primax.dinnerscan.model.DinnerScanInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DinnerScanService {

    @Resource
    private DinnerScanMapper scanMapper;

    public void addDinnerScan(DinnerScanInfo scanInfo) {
        scanMapper.insert(scanInfo);
    }


    /**
     * 测试
     * @param scanInfo
     */
    public void insertDinnerScan(DinnerScanInfo scanInfo){
        scanMapper.add(scanInfo);
        scanMapper.add(scanInfo);
        scanMapper.add(scanInfo);
    }
}
