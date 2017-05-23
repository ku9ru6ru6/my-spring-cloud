package me.cai.controller;

import lombok.extern.slf4j.Slf4j;
import me.cai.exception.JsonResponseException;
import me.cai.model.Message;
import me.cai.response.MyResponse;
import me.cai.service.MessageServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * me.cai.controller
 *
 * @author caiguangzheng
 * @date 2017/5/23
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@RestController
@Slf4j
@RequestMapping("/api/message")
public class MessageFeignController {

    private final MessageServiceFeign messageServiceFeign;

    @Autowired
    public MessageFeignController(MessageServiceFeign messageServiceFeign) {
        this.messageServiceFeign = messageServiceFeign;
    }

    @GetMapping("/initMessage")
    public List<Message> initMessage(@RequestParam Long roomId) {
        MyResponse<List<Message>> result = messageServiceFeign.initMessage(roomId);
        if (!result.isSuccess()) {
            log.error("MessageFeignController initMessage fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }
}
