package com.reminis.taskdemo.web;

import com.reminis.taskdemo.config.CronTaskRegistrar;
import com.reminis.taskdemo.po.SysJobPO;
import com.reminis.taskdemo.thread.SchedulingRunnable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author sun
 * @date 2020-08-05 14:59
 * @description
 */
@RestController
public class TestTask {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @GetMapping("/addTask")
    public String addTask(){
        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setJobId(101);
        sysJobPO.setBeanName("demoTask");
        sysJobPO.setMethodName("taskNoParams");
        sysJobPO.setJobStatus(1);
        sysJobPO.setCreateTime(new Date());
        sysJobPO.setCronExpression("0/50 * * * * ?");

        SchedulingRunnable task = new SchedulingRunnable(
                sysJobPO.getBeanName(), sysJobPO.getMethodName(), sysJobPO.getMethodParams());
        cronTaskRegistrar.addCronTask(task, sysJobPO.getCronExpression());

        return "ok";
    }

}
