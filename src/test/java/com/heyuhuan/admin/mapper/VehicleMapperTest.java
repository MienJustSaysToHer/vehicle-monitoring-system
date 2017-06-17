package com.heyuhuan.admin.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 车辆监控服务测试类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/spring.xml", "classpath*:spring/spring-datasource.xml", "classpath*:spring/spring-mybatis.xml", "classpath*:spring/spring-transaction.xml"})
public class VehicleMapperTest {

    @Resource
    private VehicleMapper vehicleMapper;

    @Test
    public void selectVehiclesTest() {
        List<String> province = Arrays.asList("黑");
        String numberPlate = null;
        System.out.println(vehicleMapper.findList());
        System.out.println(vehicleMapper.findFilterList(province, numberPlate));
        System.out.println(vehicleMapper.search(117.027027, 36.674424, 1357L));
    }

}