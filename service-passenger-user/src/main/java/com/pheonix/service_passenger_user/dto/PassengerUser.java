package com.pheonix.service_passenger_user.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("passenger_user")
public class PassengerUser {
    private long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmt_modified;

    private String passengerPhone;

    private String passengerName;

    private Integer passengerGender;

    private Integer state;
}
