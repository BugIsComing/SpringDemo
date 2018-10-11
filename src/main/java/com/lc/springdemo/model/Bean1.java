package com.lc.springdemo.model;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lc
 */
public class Bean1 {
    @Autowired
    private Bean2 bean2;
}
