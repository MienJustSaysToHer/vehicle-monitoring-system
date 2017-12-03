package com.heyuhuan.admin.dao;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;

public interface BaseDao {

    Connection getConnection();

    Admin getAdmin();

}