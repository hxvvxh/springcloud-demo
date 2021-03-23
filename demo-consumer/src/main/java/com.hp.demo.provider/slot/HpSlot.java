package com.hp.demo.provider.slot;

import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.slotchain.AbstractLinkedProcessorSlot;
import com.alibaba.csp.sentinel.slotchain.ResourceWrapper;
import com.google.common.util.concurrent.RateLimiter;

/**
 * @author hp
 * @version 1.0
 * @date 2021/3/22 22:32
 */
public class HpSlot extends AbstractLinkedProcessorSlot{

    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    public HpSlot() {
        System.out.println("HpSlot init");
    }

    @Override
    public void entry(Context context,
                      ResourceWrapper resourceWrapper,
                      Object param,
                      int count,
                      boolean prioritized, Object... args) throws Throwable {
        if (!rateLimiter.tryAcquire()) {
            System.out.println("限流中......");
        }
        System.out.println("请求成功");
        fireEntry(context, resourceWrapper, param, count, prioritized, args);
    }

    @Override
    public void exit(Context context,
                     ResourceWrapper resourceWrapper,
                     int count,
                     Object... args) {
        fireExit(context, resourceWrapper, count, args);
    }
}
