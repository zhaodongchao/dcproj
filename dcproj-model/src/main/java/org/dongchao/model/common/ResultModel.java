package org.dongchao.model.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * 对数据还回结果集的封装类
 * Created by zhaodongchao on 2017/5/14.
 */
public class ResultModel implements Serializable {
    /**
     * 结果状态 true 请求成功 false 请求失败
     */
    private boolean status;
    /**
     * 请求提示信息，可以是成功的提示或者失败的原因
     */
    private String message;
    /**
     * 请求返回的主数据，为格式化过的json字符串，没有则为""
     */
    private String result = "";

    public ResultModel(boolean status, String message, String result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public ResultModel(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResultModel() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultModel that = (ResultModel) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, result);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResultModel{");
        sb.append("status=").append(status);
        sb.append(", message='").append(message).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
