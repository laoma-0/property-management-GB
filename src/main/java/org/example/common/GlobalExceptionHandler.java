package org.example.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 捕获所有未处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> handleException(Exception e) {
        logger.error("未处理的异常：", e);
        return Result.error(500, "服务器内部错误：" + e.getMessage());
    }

    // 空指针异常处理
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result<Void> handleNullPointerException(NullPointerException e) {
        logger.error("空指针异常：", e);
        return Result.error(400, "数据不存在或为空：" + e.getMessage());
    }

    // 数据访问异常处理（数据库相关异常）
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public Result<Void> handleDataAccessException(DataAccessException e) {
        logger.error("数据库访问异常：", e);
        return Result.error(500, "数据访问失败：" + e.getMessage());
    }

    // 参数校验异常处理（@Valid注解触发）
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数校验异常：", e);
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Result.error(400, "参数校验失败");
    }

    // 绑定异常处理（请求参数绑定异常）
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result<Map<String, String>> handleBindException(BindException e) {
        logger.error("参数绑定异常：", e);
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return Result.error(400, "参数绑定失败");
    }

    // 自定义业务异常处理
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result<Void> handleBusinessException(BusinessException e) {
        logger.error("业务异常：", e);
        return Result.error(e.getCode(), e.getMessage());
    }

    // 非法参数异常处理
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<Void> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("非法参数异常：", e);
        return Result.error(400, "参数错误：" + e.getMessage());
    }
}