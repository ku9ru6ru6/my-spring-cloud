package me.cai.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * me.cai.model
 *
 * @author caiguangzheng
 * @date 2017/4/16
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Data
@NoArgsConstructor
public class Room implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 房间创建者的id
     */
    private Long userId;

    /**
     * 房间名
     */
    private String name;

    /**
     *  简介
     */
    private String intro;

    /**
     * 房间最大人数, 最大不超过20
     */
    private Integer maxNumber;

    /**
     * 创建时间
     */
    private Date createTime;
}
