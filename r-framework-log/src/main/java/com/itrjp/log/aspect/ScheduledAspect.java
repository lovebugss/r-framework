package com.itrjp.log.aspect;

import com.itrjp.log.util.MDCTraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 针对于使用{@link org.springframework.scheduling.annotation.Scheduled }标记的方法, 添加traceId. 方便日志查询.
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 17:43
 */
@Aspect
public class ScheduledAspect {
    @Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public void scheduledPoint() {
    }

    private Logger logger(JoinPoint joinPoint) {
        return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
    }

    @Around("scheduledPoint()")
    public Object round(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger log = logger(joinPoint);
        try {
            // 添加traceId
            MDCTraceUtils.putTraceId(MDCTraceUtils.createTraceId());
            log.info("enter {}()", joinPoint.getSignature().getName());
            Object result = joinPoint.proceed();
            log.info("exit: {}(), with result = {}", joinPoint.getSignature().getName(), result);
            return result;
        } finally {
            // 删除traceId
            MDCTraceUtils.removeTraceId();
        }
    }
}
