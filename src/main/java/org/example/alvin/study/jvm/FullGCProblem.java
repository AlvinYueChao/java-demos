package org.example.alvin.study.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author AlvinPower
 * @date 2021/1/25
 *
 * -XX:+PrintGC -Xms200m -Xmx200m
 * -XX:+HeapDumpOnOutOfMemoryError
 */
public class FullGCProblem {

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50, new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);
        while (true) {
            calc();
            Thread.sleep(100);
        }
    }

    private static void calc() {
        List<UserInfo> tasks = getAllCardInfo();
        tasks.forEach(userInfo -> {
            executor.scheduleWithFixedDelay(userInfo::user, 2, 3, TimeUnit.SECONDS);
        });
    }

    private static List<UserInfo> getAllCardInfo() {
        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserInfo userInfo = new UserInfo();
            userInfos.add(userInfo);
        }
        return userInfos;
    }

    private static class UserInfo {
        String name = "Alvin";
        int age = 18;
        BigDecimal money = new BigDecimal("999999.99");

        public void user () {
            // todo
        }
    }
}
