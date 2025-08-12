package org.example.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 统一响应结果封装类
 * @param <T> 响应数据类型
 */
@Data
@Schema(description = "统一响应结果")
public class Result<T> {
    /**
     * 状态码：200成功，其他为错误
     */
    @Schema(description = "状态码（200成功，其他为错误）")
    private int code;

    /**
     * 响应消息
     */
    @Schema(description = "响应消息")
    private String msg;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    // 私有构造方法，禁止直接实例化
    private Result() {}

    /**
     * 成功响应（无数据）
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.code = 200;
        result.msg = "操作成功";
        return result;
    }

    /**
     * 成功响应（带数据）
     * @param data 响应数据
     * @param <T> 数据类型
     * @return 成功响应对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.data = data;
        return result;
    }

    /**
     * 错误响应
     * @param code 错误状态码
     * @param msg 错误消息
     * @param <T> 数据类型
     * @return 错误响应对象
     */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> result = new Result<>();
        result.code = code;
        result.msg = msg;
        return result;
    }
}