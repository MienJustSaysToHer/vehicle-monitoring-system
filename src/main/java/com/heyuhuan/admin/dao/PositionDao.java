package com.heyuhuan.admin.dao;

public interface PositionDao {

    /**
     * 插入位置信息
     *
     * @param rowKey     行键
     * @param modifyDate 时刻
     * @param longitude  经度
     * @param latitude   纬度
     */
    void updatePosition(String rowKey, String modifyDate, String longitude, String latitude);

}