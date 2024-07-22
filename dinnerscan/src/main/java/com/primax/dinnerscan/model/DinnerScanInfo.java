package com.primax.dinnerscan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "DSS_Scan_Info")
public class DinnerScanInfo {

    //公司
    @TableField(value = "Company")
    private String company;

    //企业微信绑定ID
    @TableField(value = "WX_UserID")
    private String wxUserId;

    //工号
    @TableField(value = "Emp_No")
    private String empNo;

    //二维码信息
    @TableField(value = "QRcode")
    private String qrCode;

    //申报类型 0正常 1补录

    @TableField(value = "Type")
    private Integer type;

    //扫码时间
    @TableField(value = "ScanTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date scanTime;

    //备注
    @TableField(value = "Remark")
    private String remark;
}
