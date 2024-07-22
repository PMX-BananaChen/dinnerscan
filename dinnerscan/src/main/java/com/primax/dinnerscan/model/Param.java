package com.primax.dinnerscan.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Param implements Serializable {

    private static final long serialVersionUID = -5519503210032209069L;
    //接口方法名
    private String cmd;
    //版本号
    private String version;
    //参数
    private Object data;
}
