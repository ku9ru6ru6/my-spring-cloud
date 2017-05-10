package me.cai.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

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
public class JsonResponseExceptionView {

    private int status = 500;
    private String message = "unknown exception";

    public JsonResponseExceptionView(JsonResponseException e) {
        if (Objects.nonNull(e)) {
            this.setStatus(e.getCode());
            this.setMessage(e.getMessage());
        }
    }
}
