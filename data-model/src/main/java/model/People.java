package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * model
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class People {

    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
