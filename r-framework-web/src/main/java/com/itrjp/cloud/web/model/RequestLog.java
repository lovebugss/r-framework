package com.itrjp.cloud.web.model;

import java.util.Map;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 17:54
 */
public class RequestLog {
    private int duration;
    private String startTime;
    private String endTime;
    private String url;
    private Map<String, String[]> parameters;
    private String body;
    private String method;
    private Map<String, String> requestHeaders;
}
