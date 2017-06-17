package com.heyuhuan.admin.service;

import com.heyuhuan.admin.pojo.Vehicle;

import java.util.List;

/**
 * 车辆监控服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface VehicleService {

    List<Vehicle> getList();

    List<Vehicle> getList(List<String> province, String numberPlate, List<Long> area);

    int update(Vehicle vehicle);

}