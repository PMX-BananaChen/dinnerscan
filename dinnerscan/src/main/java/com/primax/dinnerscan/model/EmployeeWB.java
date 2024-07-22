package com.primax.dinnerscan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @className: EmployeeWB
 * @description: 第三方或支援人员
 * @author: Stan.Zheng
 * @date: 2021/4/26
 */

@Data
@TableName(value = "HR_Employee_WB")
public class EmployeeWB {

    @TableField(value = "Emp_No")
    private String empNo;

    @TableField(value = "Emp_Name")
    private String empName;

}
