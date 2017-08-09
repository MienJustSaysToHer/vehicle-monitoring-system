package com.heyuhuan.admin.service;

import com.heyuhuan.admin.pojo.Position;

import java.util.List;

/**
 * 位置服务类接口
 *
 * @author 何宇寰
 * @version 1.0
 * @since 1.0
 */
public interface PositionService {

    List<Position> getList(Integer toid);

}