package com.primax.dinnerscan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primax.dinnerscan.model.DinnerScanInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DinnerScanMapper extends BaseMapper<DinnerScanInfo> {

    @Insert("INSERT INTO DSS_Scan_Info ( Company, WX_UserID, Emp_No, QRcode, Type, ScanTime,Remark ) VALUES (#{company},#{wxUserId},#{empNo},#{qrCode},#{type},GETDATE(),#{remark} )")
    void add(DinnerScanInfo scanInfo);
}