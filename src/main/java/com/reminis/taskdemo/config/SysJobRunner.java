package com.reminis.taskdemo.config;

import com.reminis.taskdemo.enums.SysJobStatus;
import com.reminis.taskdemo.po.SysJobPO;
import com.reminis.taskdemo.thread.SchedulingRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

//    @Autowired
//    private ISysJobDao sysJobDao;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
//        List<SysJobPO> jobList = sysJobDao.getSysJobListByStatus(SysJobStatus.NORMAL.ordinal());

        //模拟数据
        List<SysJobPO> jobList = new ArrayList<>();
        SysJobPO sysJobPO = new SysJobPO();
        sysJobPO.setJobId(101);
        sysJobPO.setBeanName("demoTask");
        sysJobPO.setMethodName("taskNoParams");
        sysJobPO.setJobStatus(1);
        sysJobPO.setCreateTime(new Date());
        sysJobPO.setCronExpression("0/50 * * * * ?");
        jobList.add(sysJobPO);
        if (jobList != null && jobList.size() > 0) {
            for (SysJobPO job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                cronTaskRegistrar.addCronTask(task, job.getCronExpression());
            }

            logger.info("定时任务已加载完毕...");
        }
    }
}