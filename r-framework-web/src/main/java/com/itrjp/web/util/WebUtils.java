package com.itrjp.web.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

/**
 * WebUtils
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/23 17:35
 */
public final class WebUtils {

    public static Map<String, String[]> getParameters(HttpServletRequest httpRequest) {
        if (httpRequest == null) {
            return Collections.emptyMap();
        }
        return httpRequest.getParameterMap();
    }
}
