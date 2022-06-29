package com.itrjp.web.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * TODO
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 17:51
 */
@ConfigurationProperties("r.web.log")
public class RequestLogProperties {

    private List<String> headers;
}
