package com.heyuhuan.admin.service;

import com.heyuhuan.admin.dto.Command;
import com.heyuhuan.admin.dto.Json;
import com.heyuhuan.admin.pojo.Vehicle;

import java.util.List;
import java.util.Map;

/**
 * 车辆监控服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface VehicleService {

    List<Vehicle> getList(List<String> province, String numberPlate, List<Long> area);

    Json execute(Command command);

    void update(Vehicle vehicle);

    List<Map<String, Object>> getDistributionList();

}