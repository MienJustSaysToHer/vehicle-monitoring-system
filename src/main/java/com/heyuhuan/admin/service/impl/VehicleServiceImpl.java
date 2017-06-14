package com.heyuhuan.admin.service.impl;

import com.heyuhuan.admin.mapper.VehicleMapper;
import com.heyuhuan.admin.pojo.Vehicle;
import com.heyuhuan.admin.service.VehicleService;
import com.heyuhuan.admin.util.JsonUtil;
import com.heyuhuan.admin.websocket.VehicleMonitoringWebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * 车辆监控服务类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Service("vehicleServiceImpl")
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleMapper vehicleMapper;

    @Override
    public Map<Integer, Vehicle> getMap() {
        return vehicleMapper.selectVehicles();
    }

    @Override
    public int update(Vehicle vehicle) {
        try {
            VehicleMonitoringWebSocket.sendMessage(JsonUtil.toJson(vehicle));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicleMapper.updateByPrimaryKeySelective(vehicle);
    }

}