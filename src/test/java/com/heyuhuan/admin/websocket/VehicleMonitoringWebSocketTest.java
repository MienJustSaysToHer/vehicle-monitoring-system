package com.heyuhuan.admin.websocket;

import org.junit.Test;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * 车辆监控 WebSocket 测试类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public class VehicleMonitoringWebSocketTest {

    @Test
    public void testUuid() {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(Objects.equals(1, (byte) 1));
        System.out.println(1 == (byte) 1);
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2);
        System.out.println(s1 == s5);
        System.out.println(s1 == s6);
        System.out.println(s1 == s6.intern());
        System.out.println(s2 == s2.intern());
        Date date = new Date(1507197932881l);
        System.out.println(date);
    }

}