package com.example.transporter_service.dao;

import com.example.transporter_service.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDao {
    @Select("SELECT * FROM `order` WHERE userid = #{userid} AND state = #{state}")
    List<Order> findOrderByUseridAndState(int userid, int state);

    @Select("SELECT * FROM `order` WHERE transporterid = #{transporterid} AND state = #{state}")
    List<Order> findOrderByTransporterIdAndState(int transporterid, int state);

    @Select("SELECT * FROM `order` WHERE state = #{state}")
    List<Order> findOrderByState(int state);

    @Update("UPDATE `order` SET state = #{state}, starttime = #{starttime}, transporterid = #{transporterid} WHERE orderid = #{orderid}")
    int updateOrderState(int state, String starttime, int transporterid, int orderid);

    @Update("UPDATE `order` SET state = #{state}, endtime = #{endtime} WHERE orderid = #{orderid}")
    int completeOrder(int state, String endtime, int orderid);

    @Insert("INSERT INTO `order` (ordername, description, createtime, userid, state) VALUES (#{ordername}, #{description}, #{createtime}, #{userid}, #{state})")
    void insertOrder(Order order);

    @Delete("DELETE FROM `order` WHERE orderid = #{orderid}")
    void deleteOrder(int orderid);
}
