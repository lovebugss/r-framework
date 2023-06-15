package com.itrjp.web.handler;

import com.itrjp.core.exception.BizException;
import com.itrjp.core.result.GlobalErrorCode;
import com.itrjp.core.result.Result;
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
        logger.error("biz-exception: " + exception.getMessage());
        return Result.error(exception.getCode(), exception.getMessage());
    }
}
