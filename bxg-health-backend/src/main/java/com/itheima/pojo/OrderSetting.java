package com.itheima.pojo;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 预约设置
 */
public class OrderSetting implements Serializable {
    private Integer id;
    private LocalDate orderDate;// 预约设置日期
    private int number;// 可预约人数
    private int reservations;// 已预约人数

    public OrderSetting() {
    }

    public OrderSetting(LocalDate orderDate, int number) {
        this.orderDate = orderDate;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getReservations() {
        return reservations;
    }

    public void setReservations(int reservations) {
        this.reservations = reservations;
    }
}
