package com.itrjp.cloud.web.handler;

import com.itrjp.cloud.core.exception.BizException;
import com.itrjp.cloud.core.result.Result;
import com.itrjp.cloud.core.result.GlobalErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author <a href="mailto:r979668507@gmail.com">renjp</a>
 * @date 2022/6/29 16:13
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有Exception
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus
    public Result<Void> handlerException(Exception exception) {
        logger.error("exception", exception);
        return Result.error(GlobalErrorCode.SERVER_ERROR);
    }

    /**
     * 自定义业务异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public Result<Void> handlerBizException(BizException exception) {
        logger.warn("biz-exception: " + exception.getMessage());
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
