package com.heyuhuan.admin.service.impl;

import com.heyuhuan.admin.mapper.PositionMapper;
import com.heyuhuan.admin.pojo.Position;
import com.heyuhuan.admin.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 位置服务类
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
@Service("positionServiceImpl")
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> getList(Integer toid) {
        return positionMapper.findList(toid);
    }

}