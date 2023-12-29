package com.itrjp.log.filter;

import com.itrjp.core.util.StringUtils;
import com.itrjp.log.util.MDCTraceUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/30 10:24
 */
@Slf4j
public class FeignTraceInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        String traceId = MDCTraceUtils.getTraceId();
        if (StringUtils.isNotEmpty(traceId)) {
            if (log.isDebugEnabled()) {
                log.debug("增加TraceID");
            }
            requestTemplate.header(MDCTraceUtils.TRACE_HEADER_NAME, traceId);
        }
    }
}
