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
public class Message implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房间id
     */
    private Long roomId;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息创建时间
     */
    private Date createTime;
}
