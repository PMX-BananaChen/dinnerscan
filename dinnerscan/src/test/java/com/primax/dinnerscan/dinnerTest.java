package com.primax.dinnerscan;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class dinnerTest {
//    @Autowired
//    DinnerScanRest dinnerScanRest;

    /**
     * 登录service
     */
//    @Autowired
//    private PositionService positionService;

    /**
     * 就餐记录service
     */
//    @Autowired
//    private DinnerScanService scanService;
    // 初始化
    @Before
    public void setUp() throws Exception {
        System.out.println("执行初始化");
    }

    // 测试
//    @Test
//    public void test() throws Exception {
//
//        DinnerScanInfo dinnerScanInfo = new DinnerScanInfo();
////        boolean b = StringUtils.isEmpty(dinnerScanInfo);
//        dinnerScanInfo.setEmpNo("511507");
//        dinnerScanInfo.setCompany("PMX");
//        dinnerScanInfo.setWxUserId("511507");
//        dinnerScanInfo.setQrCode("D20621");
//        Result r = dinnerScanRest.addDinnerScanInfo(dinnerScanInfo);
//        System.out.println("========"+r.getMsg());
//    }

    // 测试
//    @Test
//    public void dateTimeTest() throws Exception {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
//        String format = zonedDateTime.format(formatter);
//
//        System.out.println("zonedDateTime========"+zonedDateTime.format(formatter));
//    }


       // 测试
//    @Test
//    public void qrCodeTest() throws Exception {
//        DssCodePosition position = positionService.getPosition("D20621");
//
//
//        System.out.println("zonedDateTime========"+position.toString());
//    }

    // 测试
//    @Test
//    public void codeTest() throws Exception {
//
//        DinnerScanInfo scanInfo = new DinnerScanInfo();
//        scanInfo.setCompany("PMX");
//        scanInfo.setWxUserId("511507");
////        scanInfo.setType(0);
////        scanInfo.setRemark(null);
//        scanInfo.setEmpNo("511507");
//        scanInfo.setQrCode("D20009");
//        scanService.insertDinnerScan(scanInfo);
//
//    }

    // 测试
//    @Test
//    public void sizeTset() throws Exception {
//
//        int [] a = new int[3];
//
//        a= new int[]{0};
//
//        System.out.println("```````"+a[2]);
//
//    }

}
