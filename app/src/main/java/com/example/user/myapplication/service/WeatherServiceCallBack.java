package com.example.user.myapplication.service;

import com.example.user.myapplication.data.Channel;

public interface WeatherServiceCallBack {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);

}
