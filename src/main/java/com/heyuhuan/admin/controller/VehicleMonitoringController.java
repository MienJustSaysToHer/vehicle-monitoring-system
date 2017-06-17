package com.heyuhuan.admin.controller;

import com.heyuhuan.admin.pojo.Vehicle;
import com.heyuhuan.admin.service.VehicleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 车辆监控控制类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/vehicle-monitoring")
public class VehicleMonitoringController {

    @Resource
    private VehicleService vehicleService;

    @RequestMapping(value = "/vehicles", method = RequestMethod.GET)
    public List<Vehicle> getVehicles() {
        return vehicleService.getList();
    }

    @RequestMapping(value = "/vehicles/filter", method = RequestMethod.GET)
    public List<Vehicle> filterVehicles(@RequestParam(value = "province[]", required = false) List<String> province, String numberPlate, @RequestParam(value = "area[]", required = false) List<Long> area) {
        return vehicleService.getList(province, numberPlate, area);
    }

    @RequestMapping(value = "/vehicles/{id}", method = RequestMethod.PUT)
    public String putVehicle(@PathVariable("id") Integer id, @RequestBody Vehicle vehicle) {
        vehicle.setId(id);
        vehicleService.update(vehicle);
        return "success";
    }

}