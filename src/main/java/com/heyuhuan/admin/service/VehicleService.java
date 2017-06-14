package com.heyuhuan.admin.service;

import com.heyuhuan.admin.pojo.Vehicle;

import java.util.Map;

/**
 * 车辆监控服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface VehicleService {

    Map<Integer, Vehicle> getMap();

    int update(Vehicle vehicle);

}