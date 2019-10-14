package com.example.springsample.service.impl;

import com.example.springsample.service.FooService;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {

    @Override
    public void fool() {
        System.out.println(" this is a fool");
    }
}
