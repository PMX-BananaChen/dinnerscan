package com.primax.dinnerscan.controller;


import com.primax.dinnerscan.model.*;
import com.primax.dinnerscan.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RequestMapping("/pmx/dinner/dinnerScan")
@RestController
public class DinnerScanRest  {

    private static final Logger logger = LoggerFactory.getLogger(DinnerScanRest.class);

    /**
     * 就餐记录service
     */
    @Autowired
    private DinnerScanService scanService;


    /**
     * 员工service
     */
    @Autowired
    private WxEmployeeUserService wxEmployeeUserService;


    /**
     * 登录service
     */
    @Autowired
    private PositionService positionService;

    /**
     * 餐桌座位service
     */
    @Autowired
    private LoginService loginService;

    /**
     * 员工查询service
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * 东聚获取就餐人员工号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getPmxEmployeeNo")
    @ResponseBody
    public Result getPmxEmployeeNo(HttpServletRequest request, HttpServletResponse response) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {

            //企业微信绑定ID
            String userId = request.getParameter("userId");

            if (userId == null) {
                return new Result(Result.RESULT_FAILURE, "获取微信授权失败");
            }
            WxEmployeeUser user = wxEmployeeUserService.getWxEmployeeUserByPmx(userId);
            if (user == null) {
                return new Result(Result.RESULT_FAILURE, "获取员工信息失败");
            }
            //获取就餐人员工号
            String empNo = user.getEmployeeCode();
            logger.info("进来了吗???????????????");
            logger.info("empNo=========================="+empNo);
            r.setData(empNo);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }


    /**
     * 蒂芬妮获取就餐人员工号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getTymEmployeeNo")
    @ResponseBody
    public Result getTymEmployeeNo(HttpServletRequest request, HttpServletResponse response) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {

            //企业微信绑定ID
            String userId = request.getParameter("userId");

            if (userId == null) {
                return new Result(Result.RESULT_FAILURE, "获取微信授权失败");
            }
            WxEmployeeUser user = wxEmployeeUserService.getWxEmployeeUserByTym(userId);
            if (user == null) {
                return new Result(Result.RESULT_FAILURE, "获取员工信息失败");
            }
            //获取就餐人员工号
            String empNo = user.getEmployeeCode();
            logger.info("进来了吗???????????????");
            logger.info("empNo=========================="+empNo);
            r.setData(empNo);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }


    /**
     * 新增就餐扫码记录
     * @param scanInfo
     * @return
     */
    @RequestMapping("/addDinnerScanInfo")
    @ResponseBody
    public Result addDinnerScanInfo(@RequestBody DinnerScanInfo scanInfo) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {
            //WxUserId是否要去掉
            //|| scanInfo.getWxUserId() == null ||scanInfo.getWxUserId() == ""
            if (scanInfo == null ){
                return r;
            }
            //验证二维码
            if(scanInfo.getQrCode() == null){
                return new Result(2, "无效的二维码~");
            }
            DssCodePosition position = positionService.getPosition(scanInfo.getQrCode());
            if (position == null){
                return new Result(2, "无效的二维码~");
            }
            //完整工号
            String empNo = scanInfo.getEmpNo();
            if (empNo.length() == 3){
                scanInfo.setEmpNo("0"+empNo);
            }else if(empNo.length() == 2){
                scanInfo.setEmpNo("00"+empNo);
            }
            scanInfo.setScanTime(new Date());
            scanService.addDinnerScan(scanInfo);
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }


    /**
     * 申报补录管理员登录
     * @param dssSysUser
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestBody DssSysUser dssSysUser) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {

            if (dssSysUser == null){
                return r;
            }
            DssSysUser user = loginService.getUser(dssSysUser);
            logger.info("user.getUserName()"+user.getUserName());
//            if (!user.getUserName().equals(dssSysUser.getUserName())){
//                return r;
//            }
            r.setData(user.getUserName());
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }

    /**
     * 申报补录管理员登录
     * @param scanInfo
     * @return
     */
    @RequestMapping("/addDinnerScanInfoByUser")
    @ResponseBody
    public Result addDinnerScanInfoByUser(@RequestBody DinnerScanInfo scanInfo) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {

            if (scanInfo == null ){
                return r;
            }

            String empNo = scanInfo.getEmpNo();
            if (empNo.length() == 3){
                empNo="0"+empNo;
                scanInfo.setEmpNo("0"+empNo);
            }else if(empNo.length() == 2){
                empNo="00"+empNo;
                scanInfo.setEmpNo("00"+empNo);
            }

            String qrCode = scanInfo.getQrCode();

            //校验餐位
            boolean status = false;
            DssCodePosition position = positionService.getPosition(qrCode);
            if (position == null){
                return new Result(2, "填写的餐位桌号有误");
            }
            if ("PMX".equals(scanInfo.getCompany()) || "THA".equals(scanInfo.getCompany())){
                status = employeeService.getEmoloyee(empNo);
            }else if("TYM".equals(scanInfo.getCompany())){
                status = employeeService.getEmoloyeeTYM(empNo);
            }
            //校验工号
            if (status){
                return new Result(3, "填写的工号有误");
            }

            //设置申报类型为补录
            scanInfo.setType(1);
            scanInfo.setRemark("餐位申报类型为补录");

            scanInfo.setScanTime(new Date());

            scanService.addDinnerScan(scanInfo);

            r.setData("1");
            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }

    /**
     * 泰国厂区餐位申报
     * @param scanInfo
     * @return
     */
    @RequestMapping("/addDinnerScanInfoByTHA")
    @ResponseBody
    public Result addDinnerScanInfoByTHA(@RequestBody DinnerScanInfo scanInfo) {
        Result r = new Result(Result.RESULT_FAILURE, Result.RESULT_FAILURE_MSG);

        try {
            if (scanInfo == null ){
                return r;
            }
            //校验工号
            Boolean status = employeeService.getEmoloyeeTH(scanInfo.getEmpNo());
            if (status){
                return new Result(2, "กรอกรหัสพนักงานผิดn"+"\n"+"填写的工号有误");
            }

            //东7区时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            Date date = sdf.parse(zonedDateTime.format(formatter));
            scanInfo.setScanTime(date);

            scanService.addDinnerScan(scanInfo);

            r.setCode(Result.RESULT_SUCCESS);
            r.setMsg(Result.RESULT_SUCCESS_MSG);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new Result(Result.RESULT_FAILURE, "系统异常,请联系管理员");
        }
        return r;
    }

}
