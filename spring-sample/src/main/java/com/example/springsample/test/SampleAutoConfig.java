package com.example.springsample.test;

import java.util.UUID;

public class SampleAutoConfig  implements  SampleAutoConfigInterface {

    @Override
    public void say() {
        System.out.println(" str - >" + UUID.randomUUID().toString());
    }
}
