package com.mee.common.util;

import java.io.Serializable;

/**
 * MEE响应体
 *
 * @author shaoow
 * @version 1.0
 * @className MeeResult
 * @date 2023/6/1 14:12
 */
public class MeeResult<D> implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 状态值定义(status)：
     *      0:  失败
     *      1:  成功
     *      2:  padding
     */

    /**
     * 状态
     */
    private Integer status=1;

    /**
     * 描述
     */
    private String msg="success";

    private Long timestamp=null;

    /**
     * 响应数据
     */
    private D data;

    public MeeResult() {
        // 空构造，默认就是success
        this.timestamp=System.currentTimeMillis();
    }

    public MeeResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.timestamp=System.currentTimeMillis();
    }

    public MeeResult(Integer status, String msg, D data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.timestamp=System.currentTimeMillis();
    }

    //////// getter setter

    public Integer getStatus() {
        return status;
    }

    public MeeResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MeeResult<D> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public MeeResult<D> setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public D getData() {
        return data;
    }

    public MeeResult<D> setData(D data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "MeeResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", timestamp=" + timestamp +
                ", data=" + data +
                '}';
    }

}
