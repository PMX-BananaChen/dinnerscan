package com.primax.dinnerscan.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.primax.dinnerscan.mapper.EmployeeMapper;
import com.primax.dinnerscan.mapper.EmployeeTHMapper;
import com.primax.dinnerscan.mapper.EmployeeTYMMapper;
import com.primax.dinnerscan.mapper.EmployeeWBMapper;
import com.primax.dinnerscan.model.Employee;
import com.primax.dinnerscan.model.EmployeeTH;
import com.primax.dinnerscan.model.EmployeeTYM;
import com.primax.dinnerscan.model.EmployeeWB;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private EmployeeTYMMapper employeeTYMMapper;

    @Resource
    private EmployeeTHMapper employeeTHMapper;

    @Resource
    private EmployeeWBMapper employeeWBMapper;

    public boolean getEmoloyee(String empNo) {

        Employee employee = employeeMapper.selectOne(
                    new QueryWrapper<Employee>()
                            .eq("Emp_No", empNo)
            );

        return StringUtils.isEmpty(employee);
    }

    public boolean getEmoloyeeTYM(String empNo) {

        EmployeeTYM employeeTYM = employeeTYMMapper.selectOne(
                    new QueryWrapper<EmployeeTYM>()
                            .eq("Emp_No", empNo)
            );
        return StringUtils.isEmpty(employeeTYM);
    }

    public boolean getEmoloyeeTH(String empNo) {

        EmployeeTH employeeTH = employeeTHMapper.selectOne(
                new QueryWrapper<EmployeeTH>()
                        .eq("Emp_No", empNo)
        );

        EmployeeWB employeeWB = employeeWBMapper.selectOne(
                new QueryWrapper<EmployeeWB>()
                        .eq("Emp_No", empNo)
        );
        return StringUtils.isEmpty(employeeTH) && StringUtils.isEmpty(employeeWB);
    }
}
