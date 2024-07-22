package com.primax.dinnerscan.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "HR_Employee_TYM")
public class EmployeeTYM {

    @TableField(value = "Emp_No")
    private String empNo;

    @TableField(value = "Emp_Name")
    private String empName;
}
