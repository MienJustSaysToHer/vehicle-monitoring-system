package com.heyuhuan.admin.mapper;

import com.heyuhuan.admin.pojo.TransportOrder;

public interface TransportOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    int insert(TransportOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    int insertSelective(TransportOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    TransportOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TransportOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transport_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TransportOrder record);
}