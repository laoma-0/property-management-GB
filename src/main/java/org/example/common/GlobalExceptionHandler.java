package org.example.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 捕获所有异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Void> handleException(Exception e) {
        // 日志记录异常信息（实际项目中建议使用日志框架）
        e.printStackTrace();
        // 返回错误响应
        return Result.error(500, "服务器异常：" + e.getMessage());
    }

    // 可添加特定异常的处理（如空指针、参数异常等）
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result<Void> handleNullPointerException(NullPointerException e) {
        return Result.error(400, "数据不存在：" + e.getMessage());
    }
}
