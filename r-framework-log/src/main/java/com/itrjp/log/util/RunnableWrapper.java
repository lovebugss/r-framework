package com.itrjp.log.util;

public class RunnableWrapper implements Runnable {
    private final Runnable runnable;
    private final String traceId;

    public RunnableWrapper(Runnable runnable) {
        this.runnable = runnable;
        this.traceId = MDCTraceUtils.getTraceId();
    }

    public static Runnable of(Runnable runnable) {
        return new RunnableWrapper(runnable);
    }

    @Override
    public void run() {
        try {
            // 添加traceId
            MDCTraceUtils.putTraceId(this.traceId);
            runnable.run();
        } finally {
            // 删除traceId
            MDCTraceUtils.removeTraceId();
        }

    }
}
