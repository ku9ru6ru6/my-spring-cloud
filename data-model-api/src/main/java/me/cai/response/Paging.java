package me.cai.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * me.cai.response
 *
 * @author caiguangzheng
 * @date 2017/5/11
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Data
@NoArgsConstructor
public class Paging<T> {

    private Long total;
    private List<T> data;

    public Paging(Long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public Boolean isEmpty() {
        return Objects.equals(0L, this.total) || this.data == null || this.data.isEmpty();
    }

}
