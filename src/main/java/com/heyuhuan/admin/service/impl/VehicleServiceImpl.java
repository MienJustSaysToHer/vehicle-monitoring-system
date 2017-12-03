package com.heyuhuan.admin.service.impl;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.heyuhuan.admin.dao.PositionDao;
import com.heyuhuan.admin.dto.Command;
import com.heyuhuan.admin.dto.Json;
import com.heyuhuan.admin.mapper.TransportOrderMapper;
import com.heyuhuan.admin.mapper.VehicleMapper;
import com.heyuhuan.admin.mapper.XxAreaMapper;
import com.heyuhuan.admin.pojo.TransportOrder;
import com.heyuhuan.admin.pojo.Vehicle;
import com.heyuhuan.admin.pojo.XxArea;
import com.heyuhuan.admin.service.PushService;
import com.heyuhuan.admin.service.VehicleService;
import com.heyuhuan.admin.util.JsonUtil;
import com.heyuhuan.admin.websocket.VehicleMonitoringWebSocket;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

    @Resource
    private TransportOrderMapper transportOrderMapper;

    @Resource(name = "scheduler")
    private TaskScheduler taskScheduler;

    @Resource
    private PushService pushService;

    @Resource
    private XxAreaMapper xxAreaMapper;

    @Resource
    private PositionDao positionDao;

    @Override
    public List<Vehicle> getList(List<String> province, String numberPlate, List<Long> area, Byte state) {
        List<Vehicle> vehicles = vehicleMapper.findList(province, numberPlate, state);
        Iterator<Vehicle> vehicleIterator = vehicles.iterator();
        while (vehicleIterator.hasNext()) {
            Vehicle vehicle = vehicleIterator.next();
            if (area != null && area.size() != 0 && vehicleMapper.search(vehicle.getLongitude(), vehicle.getLatitude(), area.get(area.size() - 1)) <= 0) {
                vehicleIterator.remove();
                continue;
            }
            if (vehicle.getState() == 1) {
                TransportOrder transportOrder = transportOrderMapper.selectByVehicleid(vehicle.getId(), vehicle.getState());
                vehicle.setTonumber(transportOrder.getTonumber());
                vehicle.setLoadweight(vehicle.getLoadweight() - transportOrder.getWeight());
            }
        }
        return vehicles;
    }

    @Override
    public Json execute(Command command) {
        if (command.getAssertTime()) {
            taskScheduler.schedule(new Runnable() {
                @Override
                public void run() {
                    switch (command.getType()) {
                        case 0:
                            pushService.push(PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(command.getPhone())).setNotification(Notification.alert("您有新的运输订单")).setMessage(Message.newBuilder().setMsgContent("您有新的运输订单").addExtra("id", command.getId()).build()).build());
                            break;
                        case 1:
                            pushService.push(PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(command.getPhone())).setNotification(Notification.alert(command.getContent())).build());
                            break;
                        default:
                            break;
                    }
                }
            }, command.getTime());
            Json j = new Json();
            j.setMsg("发送命令成功，命令将在指定的时间执行");
            j.setSuccess(true);
            return j;
        } else {
            switch (command.getType()) {
                case 0:
                    return pushService.push(PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(command.getPhone())).setNotification(Notification.alert("您有新的运输订单")).setMessage(Message.newBuilder().setMsgContent("您有新的运输订单").addExtra("id", command.getId()).build()).build());
                case 1:
                    return pushService.push(PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(command.getPhone())).setNotification(Notification.alert(command.getContent())).build());
                default:
                    Json j = new Json();
                    j.setMsg("发送失败，命令类型错误");
                    j.setSuccess(true);
                    return j;
            }
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        if (vehicle == null || vehicle.getPhone() == null) {
            return;
        }

        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String modifyDate = simpleDateFormat.format(now);
        if (vehicle.getLongitude() != null && vehicle.getLatitude() != null) {
            positionDao.updatePosition(vehicle.getPhone() + modifyDate, modifyDate, vehicle.getLongitude().toString(), vehicle.getLatitude().toString());
        }

        if (vehicle.getProvince() != null || vehicle.getNumberPlate() != null || vehicle.getType() != null || vehicle.getState() != null || vehicle.getTemperature() != null || vehicle.getHumidity() != null || vehicle.getLoadweight() != null || vehicle.getLoadvolume() != null) {
            vehicle.setModifyDate(now);
            Vehicle vehicle2 = vehicleMapper.selectByPhone(vehicle.getPhone());
            if (vehicle2 == null) {
                vehicle.setCreateDate(now);
                vehicle.setState((byte) 0);
                vehicleMapper.insertSelective(vehicle);
            } else {
                vehicleMapper.updateByPhone(vehicle);
            }
        }

        if (vehicle.getLongitude() != null || vehicle.getLatitude() != null || vehicle.getProvince() != null || vehicle.getNumberPlate() != null || vehicle.getType() != null || vehicle.getState() != null || vehicle.getTemperature() != null || vehicle.getHumidity() != null || vehicle.getLoadweight() != null || vehicle.getLoadvolume() != null) {
            try {
                VehicleMonitoringWebSocket.sendMessage(JsonUtil.toJson(vehicle));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Map<String, Object>> getDistributionList() {
        List<Map<String, Object>> distribution = new ArrayList<>();
        List<XxArea> xxAreas = xxAreaMapper.findList();
        List<Vehicle> vehicles = vehicleMapper.findList(null, null, null);
        for (XxArea x : xxAreas) {
            Iterator<Vehicle> vehicleIterator = vehicles.iterator();
            Integer amount = 0;
            while (vehicleIterator.hasNext()) {
                Vehicle vehicle = vehicleIterator.next();
                if (vehicleMapper.search(vehicle.getLongitude(), vehicle.getLatitude(), x.getId()) > 0) {
                    amount++;
                }
            }
            Map<String, Object> vehicleAmount = new HashMap<>();
            if (x.getName().contains("内蒙古") || x.getName().contains("黑龙江")) {
                vehicleAmount.put("name", x.getName().substring(0, 3));
            } else {
                vehicleAmount.put("name", x.getName().substring(0, 2));
            }
            vehicleAmount.put("value", amount);
            if (x.getName().contains("内蒙古")) {
                vehicleAmount.put("value", 0);
            }
            distribution.add(vehicleAmount);
        }
        return distribution;
    }

}