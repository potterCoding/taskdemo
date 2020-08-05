package com.reminis.taskdemo.config;

import org.springframework.stereotype.Component;

/**
 * @author sun
 * @date 2020-08-05 11:23
 * @description
 */
@Component("demoTask")
public class DemoTask {
    public void taskWithParams(String params) {
        System.out.println("执行有参示例任务：" + params);
    }

    public void taskNoParams() {
        System.out.println("执行无参示例任务");
    }
}
