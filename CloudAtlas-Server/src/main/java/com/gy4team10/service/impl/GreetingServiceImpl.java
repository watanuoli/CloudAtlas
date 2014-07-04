package com.gy4team10.service.impl;

import com.gy4team10.service.GreetingService;

/**
 * Created by zhou on 14-6-11.
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String greet(String who) {
        return String.format("hello, %s!", who);
    }
}
