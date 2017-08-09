package com.heyuhuan.admin.controller;

import com.heyuhuan.admin.dto.Command;
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
import java.util.Map;

/**
 * 车辆监控控制类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleMonitoringController {

    @Resource
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> getVehicles(@RequestParam(value = "province[]", required = false) List<String> province, String numberPlate, @RequestParam(value = "area[]", required = false) List<Long> area, Byte state) {
        return vehicleService.getList(province, numberPlate, area, state);
    }

    @RequestMapping(value = "/command/{phone}", method = RequestMethod.POST)
    public String commandVehicle(@PathVariable("phone") String phone, @RequestBody Command command) {
        command.setPhone(phone);
        vehicleService.execute(command);
        return "success";
    }

    @RequestMapping(value = "/{phone}", method = RequestMethod.POST)
    public String postVehicle(@PathVariable("phone") String phone, @RequestBody Vehicle vehicle) {
        vehicle.setPhone(phone);
        vehicleService.update(vehicle);
        return "success";
    }

    @RequestMapping(value = "/distribution", method = RequestMethod.GET)
    public List<Map<String, Object>> getVehicleDistribution() {
        return vehicleService.getDistributionList();
    }

}