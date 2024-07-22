package com.primax.dinnerscan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DSS_SYS_User")
public class DssSysUser {

    @TableField(value = "UserName")
    private String userName;

    @TableField(value = "Password")
    private String password;
}
