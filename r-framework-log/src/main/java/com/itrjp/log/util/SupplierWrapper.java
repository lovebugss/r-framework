package com.itrjp.log.util;

import java.util.function.Supplier;

public class SupplierWrapper<T> implements Supplier<T> {
    private final Supplier<T> supplier;
    private final String traceId;

    private SupplierWrapper(Supplier<T> supplier) {
        this.supplier = supplier;
        this.traceId = MDCTraceUtils.getTraceId();
    }

    public static <T> Supplier<T> of(Supplier<T> supplier) {
        return new SupplierWrapper<>(supplier);
    }

    @Override
    public T get() {
        try {
            // 添加traceId
            MDCTraceUtils.putTraceId(this.traceId);
            return supplier.get();
        } finally {
            // 删除traceId
            MDCTraceUtils.removeTraceId();
        }

    }
}
