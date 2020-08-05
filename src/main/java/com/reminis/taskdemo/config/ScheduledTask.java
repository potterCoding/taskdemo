package com.reminis.taskdemo.config;

import java.util.concurrent.ScheduledFuture;

/**
 * @author sun
 * @date 2020-08-05 9:54
 * @description ScheduledFuture的包装类
 * ScheduledFuture是ScheduledExecutorService定时任务线程池的执行结果。
 */
public final class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }

}
