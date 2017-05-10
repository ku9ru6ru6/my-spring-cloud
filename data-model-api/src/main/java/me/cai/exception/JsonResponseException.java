package me.cai.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * me.cai.exception
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Data
@NoArgsConstructor
public class JsonResponseException extends RuntimeException {

    private int code = 500;
    private String message = "unknown exception";

    public JsonResponseException(String message) {
        this.message = message;
    }

    public JsonResponseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResponseException(int code, String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.code = code;
    }

    public JsonResponseException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public JsonResponseException(int code, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.code = code;
    }

    public JsonResponseException(Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
    }
}
