package com.itrjp.web.filter;

import com.itrjp.log.util.MDCTraceUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.itrjp.log.util.MDCTraceUtils.TRACE_HEADER_NAME;

/**
 * 添加traceId
 *
 * @author renjp
 * @date 2022/4/27 23:59
 */
@Configuration
@WebFilter(value = "/**")
@Order(Integer.MIN_VALUE)
public class TraceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDCTraceUtils.addTraceId();
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.addHeader(TRACE_HEADER_NAME, MDCTraceUtils.getTraceId());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
