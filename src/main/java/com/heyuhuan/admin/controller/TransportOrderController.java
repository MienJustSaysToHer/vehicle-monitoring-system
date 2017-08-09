package com.heyuhuan.admin.controller;

import com.heyuhuan.admin.pojo.Position;
import com.heyuhuan.admin.pojo.TransportOrder;
import com.heyuhuan.admin.service.PositionService;
import com.heyuhuan.admin.service.TransportOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运输订单控制类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/transportorders")
public class TransportOrderController {

    @Resource
    private TransportOrderService transportOrderService;

    @Resource
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.GET)
    public List<TransportOrder> getVehicles(Byte state) {
        return transportOrderService.getList(state);
    }

    @RequestMapping(value = "/positions", method = RequestMethod.GET)
    public List<Position> getPositions(Integer id) {
        return positionService.getList(id);
    }

}