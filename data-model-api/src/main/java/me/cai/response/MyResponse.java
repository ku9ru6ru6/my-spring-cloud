package me.cai.response;

import lombok.Data;

import java.io.Serializable;

/**
 * me.cai.response
 *
 * @author caiguangzheng
 * @date 2017/4/19
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Data
public class MyResponse<T> implements Serializable {

    private boolean isSuccess;

    private T result;

    private String error;

    private Integer code;

    private MyResponse() {}

    public static <T> MyResponse<T> ok(T result) {
        MyResponse<T> response = new MyResponse<>();
        response.setResult(result);
        response.setSuccess(true);
        response.setCode(200);
        return response;
    }

    public static <T> MyResponse<T> fail(String error) {
        return fail(error, 500);
    }

    public static <T> MyResponse<T> fail(String error, Integer code) {
        MyResponse<T> response = new MyResponse<>();
        response.setError(error);
        response.setSuccess(false);
        response.setCode(code);
        return response;
    }
}
