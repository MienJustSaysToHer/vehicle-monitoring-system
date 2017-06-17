package com.heyuhuan.admin.service.impl;

import com.heyuhuan.admin.mapper.VehicleMapper;
import com.heyuhuan.admin.pojo.Vehicle;
import com.heyuhuan.admin.service.VehicleService;
import com.heyuhuan.admin.util.JsonUtil;
import com.heyuhuan.admin.websocket.VehicleMonitoringWebSocket;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    public List<Vehicle> getList() {
        return vehicleMapper.findList();
    }

    @Override
    public List<Vehicle> getList(List<String> province, String numberPlate, List<Long> area) {
        List<Vehicle> vehicles = vehicleMapper.findFilterList(province, numberPlate);
        if (area != null && area.size() != 0) {
            Iterator<Vehicle> vehicleIterator = vehicles.iterator();
            while (vehicleIterator.hasNext()) {
                Vehicle vehicle = vehicleIterator.next();
                if (vehicleMapper.search(vehicle.getLongitude(), vehicle.getLatitude(), area.get(area.size() - 1)) <= 0) {
                    vehicleIterator.remove();
                }
            }
        }
        return vehicles;
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