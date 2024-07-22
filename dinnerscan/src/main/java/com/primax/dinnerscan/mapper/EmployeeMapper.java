package com.primax.dinnerscan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.primax.dinnerscan.model.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
