package com.vladis1350.bean;

import java.io.Serializable;

public enum OrderStatuses implements Serializable {
    ACCEPTED_FOR_PROCESSING("Принят к обработке"),
    IN_PROCESSING("В обработке"),
    CONFIRMED("Подтвержден"),
    RENOUNCEMENT("Отказ"),
    BOOKED("Заказан");

    private String info;

    public String getInfo(){
        return info;
    }

    OrderStatuses(String info) {
        this.info = info;
    }
}
