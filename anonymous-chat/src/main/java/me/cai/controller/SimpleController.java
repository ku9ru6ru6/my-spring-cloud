package me.cai.controller;

import me.cai.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * me.cai.controller
 *
 * @author caiguangzheng
 * @date 2017/4/16
 * Mail: caiguangzheng@terminus.io
 * TODO: 简单的事例
 */
@Controller
public class SimpleController {

    @MessageMapping("/message")
    @SendTo("/topic/greetings")
    public User getName(@Payload User user) {
        System.out.println(user);
        user.setName("hello " + user.getName());
        return user;
    }

    @SubscribeMapping("/init")
    public String init() {
        return "init";
    }

}
