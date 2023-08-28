package com.itrjp.cloud.web.filter;

import com.itrjp.cloud.web.util.RequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 16:19
 */
@Component
@WebFilter(value = "/**")
public class RequestLogFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();
        Map<String, String> parameterMap = RequestUtils
                .getParamMap(cachingRequestWrapper);
        String queryParam = cachingRequestWrapper.getQueryString();
        String method = cachingRequestWrapper.getMethod();

        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper);
        int status = cachingResponseWrapper.getStatus();

        // 响应体
        String responseBody = new String(cachingResponseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);

        logger.info("url: {}, method: {}, queryStr: {}, param: {}, statusCode: {}, result: {}, duration: {}", request.getRequestURL(), method, queryParam, parameterMap, status, responseBody, System.currentTimeMillis() - start);

        cachingResponseWrapper.copyBodyToResponse();

    }
}
