package com.heyuhuan.admin.service.impl;

import com.heyuhuan.admin.mapper.TransportOrderMapper;
import com.heyuhuan.admin.pojo.TransportOrder;
import com.heyuhuan.admin.service.TransportOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 运输订单服务类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Service("transportOrderServiceImpl")
public class TransportOrderServiceImpl implements TransportOrderService {

    @Resource
    private TransportOrderMapper transportOrderMapper;

    @Override
    public List<TransportOrder> getList(Byte state) {
        return transportOrderMapper.findList(state);
    }

}