package com.heyuhuan.admin.dao.impl;

import com.heyuhuan.admin.dao.BaseDao;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Repository
public class BaseDaoImpl implements BaseDao {

    private static Configuration configuration;
    private static Connection connection;
    private static Admin admin;

    @PostConstruct
    public void init() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            System.setProperty("hadoop.home.dir", "D:\\code\\winutils");
        }
        configuration = HBaseConfiguration.create();
        try {
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void close() {
        try {
            if (admin != null) {
                admin.close();
            }
            if (null != connection) {
                connection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public Admin getAdmin() {
        return admin;
    }

}