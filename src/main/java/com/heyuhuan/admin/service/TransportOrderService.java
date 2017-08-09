package com.heyuhuan.admin.service;

import com.heyuhuan.admin.pojo.TransportOrder;

import java.util.List;

/**
 * 运输订单服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface TransportOrderService {

    List<TransportOrder> getList(Byte state);

}