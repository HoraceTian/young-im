package cn.young.im.common.threadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName YoungImThreadFactroy
 * @Description
 * @date 2023/5/16 15:00
 * @Author yanceysong
 * @Version 1.0
 */
public class YoungImThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    boolean isDaemon;

    YoungImThreadFactory(String threadName, boolean daemon) {
        group = Thread.currentThread().getThreadGroup();
        namePrefix = threadName
                + "-pool-"
                + poolNumber.getAndIncrement()
                + "-thread-";
        isDaemon = daemon;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(group, runnable,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        thread.setDaemon(isDaemon);
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}