package com.itrjp.log.util;

import com.itrjp.core.util.StringUtils;
import org.slf4j.MDC;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.UUID;

/**
 * MDCTraceUtils
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 17:45
 */
public class MDCTraceUtils {

    public static final String TRACE_ID = "traceId";
    public static final String TRACE_HEADER_NAME = "x-request-id";

    public static void addTraceId() {
        MDC.put(TRACE_ID, createTraceId());
    }

    public static void putTraceId(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void removeTraceId() {
        MDC.remove(TRACE_ID);
    }

    public static String createTraceId() {
        String skwTraceId = TraceContext.traceId();
        return StringUtils.isNotEmpty(skwTraceId) ? skwTraceId : UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
