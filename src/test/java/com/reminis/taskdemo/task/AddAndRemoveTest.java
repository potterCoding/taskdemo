package com.reminis.taskdemo.task;

import com.reminis.taskdemo.config.CronTaskRegistrar;
import com.reminis.taskdemo.po.SysJobPO;
import com.reminis.taskdemo.thread.SchedulingRunnable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * @author sun
 * @date 2020-08-05 11:26
 * @description
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AddAndRemoveTest {

    private SysJobPO sysJobPO;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Before
    public void init(){
        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setJobId(101);
        sysJobPO.setBeanName("demoTask");
        sysJobPO.setMethodName("taskNoParams");
        sysJobPO.setJobStatus(1);
        sysJobPO.setCreateTime(new Date());
        sysJobPO.setCronExpression("0/50 * * * * ?");
        this.sysJobPO = sysJobPO;
    }

    //测试添加
    @Test
    public void testAdd(){
        // 连接数据库，在数据库中新增一条定时任务数据

        // 添加失败，则返回，添加成功，则加如任务
        SchedulingRunnable task = new SchedulingRunnable(
                sysJobPO.getBeanName(), sysJobPO.getMethodName(), sysJobPO.getMethodParams());
        cronTaskRegistrar.addCronTask(task, sysJobPO.getCronExpression());
    }

    //测试编辑 先移除原来的任务，再启动新任务
    @Test
    public void testEdit() {
        // 先从数据库中查询，是否存在该任务

        //先移除再添加
        SchedulingRunnable task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(), sysJobPO.getMethodParams());
        cronTaskRegistrar.removeCronTask(task);

        task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(), sysJobPO.getMethodParams());
        cronTaskRegistrar.addCronTask(task, sysJobPO.getCronExpression());
    }

    //删除定时任务
    @Test
    public void testRemove() {
        SchedulingRunnable task = new SchedulingRunnable(sysJobPO.getBeanName(), sysJobPO.getMethodName(), sysJobPO.getMethodParams());
        cronTaskRegistrar.removeCronTask(task);
    }

    //定时任务启动/停止状态切换


}
