package com.itrjp.log.filter;

import com.itrjp.core.util.StringUtils;
import com.itrjp.log.util.MDCTraceUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itrjp.log.util.MDCTraceUtils.TRACE_HEADER_NAME;

/**
 * WebTraceFilter 添加traceId
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/30 10:13
 */
@Configuration
@WebFilter(value = "/**")
@Order(Integer.MIN_VALUE)
@ConditionalOnClass({Filter.class, HttpServletRequest.class})
public class WebTraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            String traceId = ((HttpServletRequest) servletRequest).getHeader(TRACE_HEADER_NAME);
            if (StringUtils.isNotEmpty(traceId)) {
                MDCTraceUtils.putTraceId(traceId);
            } else {
                MDCTraceUtils.addTraceId();
            }
        }

        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.addHeader(TRACE_HEADER_NAME, MDCTraceUtils.getTraceId());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
