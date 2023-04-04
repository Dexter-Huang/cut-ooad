package com.exchange.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse implements Serializable {

    private Integer code;
    /**
     * 数据
     */
    private Object data;
    /**
     * 描述
     */
    private String msg;
    public ApiResponse(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    /**
     * 成功，不传⼊数据
     * @return
     */
    public static ApiResponse success() {
        return new ApiResponse(200, null, null);
    }
    /**
     * 成功，传⼊数据
     * @param data
     * @return
     */
    public static ApiResponse success(Object data) {
        return new ApiResponse(200,null,data);
    }
    public static ApiResponse success(String msg, Object data) {
        return new ApiResponse(200,msg,data);
    }
    /**
     * 失败，传⼊描述信息
     * @param msg
     * @return
     */
    public static ApiResponse fail(String msg) {
        return new ApiResponse(400,  msg,null);
    }
    public static ApiResponse fail(String msg, Object data) {
        return new ApiResponse(400,msg, data);
    }
    public static ApiResponse fail(int code, String msg, Object data) {
        return new ApiResponse(code,msg, data);
    }


}
