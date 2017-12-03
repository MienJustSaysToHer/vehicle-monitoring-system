package com.heyuhuan.admin.dao.impl;

import com.heyuhuan.admin.dao.BaseDao;
import com.heyuhuan.admin.dao.PositionDao;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;

@Repository
public class PositionDaoImpl implements PositionDao {

    @Resource
    private BaseDao baseDao;

    @Override
    public void updatePosition(String rowKey, String modifyDate, String longitude, String latitude) {
        try {
            Table table = baseDao.getConnection().getTable(TableName.valueOf("Position"));
            Put put = new Put(rowKey.getBytes());
            put.addColumn("info".getBytes(), "modify_date".getBytes(), modifyDate.getBytes());
            put.addColumn("info".getBytes(), "longitude".getBytes(), longitude.getBytes());
            put.addColumn("info".getBytes(), "latitude".getBytes(), latitude.getBytes());
            table.put(put);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}