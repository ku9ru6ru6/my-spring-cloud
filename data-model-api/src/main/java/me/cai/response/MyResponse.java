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
public class MyResponse <T> implements Serializable {

    private boolean isSuccess;

    private T result;

    private String error;

    private MyResponse() {}

    public static <T> MyResponse ok(T result) {
        MyResponse<T> response = new MyResponse<>();
        response.setResult(result);
        response.setSuccess(true);
        return response;
    }

    public static MyResponse fail(String error) {
        MyResponse response = new MyResponse<>();
        response.setError(error);
        response.setSuccess(false);
        return response;
    }
}
