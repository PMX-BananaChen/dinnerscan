package com.primax.dinnerscan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DSS_Code_Position")
public class DssCodePosition {

    @TableField(value = "PositionID")
    private Integer positionID;

    @TableField(value = "PositionNo")
    private String positionNo;

    @TableField(value = "QRcode")
    private String qrCode;

    @TableField(value = "RoomNo")
    private String roomNo;

    @TableField(value = "PositionName")
    private String positionName;
}
