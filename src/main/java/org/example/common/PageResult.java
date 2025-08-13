package org.example.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 分页响应结果封装类
 *
 * @param <T> 响应数据类型
 */
@Data
@Schema(description = "分页响应结果")
public class PageResult<T> {
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
    private List<T> data;

    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private long total;

    /**
     * 当前页码
     */
    @Schema(description = "当前页码")
    private int pageNum;

    /**
     * 每页记录数
     */
    @Schema(description = "每页记录数")
    private int pageSize;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    private int totalPages;

    // 私有构造方法，禁止直接实例化
    private PageResult() {
    }

    /**
     * 成功响应（分页数据）
     *
     * @param data       分页数据
     * @param total      总记录数
     * @param pageNum    当前页码
     * @param pageSize   每页记录数
     * @param <T>        数据类型
     * @return 成功响应对象
     */
    public static <T> PageResult<T> success(List<T> data, long total, int pageNum, int pageSize) {
        PageResult<T> result = new PageResult<>();
        result.code = 200;
        result.msg = "操作成功";
        result.data = data;
        result.total = total;
        result.pageNum = pageNum;
        result.pageSize = pageSize;
        result.totalPages = (int) Math.ceil((double) total / pageSize);
        return result;
    }

    /**
     * 错误响应
     *
     * @param code 错误状态码
     * @param msg  错误消息
     * @param <T>  数据类型
     * @return 错误响应对象
     */
    public static <T> PageResult<T> error(int code, String msg) {
        PageResult<T> result = new PageResult<>();
        result.code = code;
        result.msg = msg;
        return result;
    }
}