package me.cai.controller;

import me.cai.model.Message;
import org.apache.ibatis.session.SqlSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Resource
    private SimpMessagingTemplate template;

    @Resource
    protected SqlSession sqlSession;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    private final String sqlId(String methodName) {
        return "me.cai.model.Message." + methodName;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }

    @MessageMapping("/message")
    public void getName(@Payload Message message) {
        System.out.println(message);
        message.setCreateTime(new Date());
        String payload = "/topic/" + message.getRoomId();
        template.convertAndSend(payload, message);
        executorService.execute(() -> sqlSession.insert(sqlId("createMessage"), message));
    }

    @RequestMapping("/initMessage")
    @ResponseBody
    public List<Message> initMessage(@RequestParam int roomId) {
        List<Message> messages = sqlSession.selectList(sqlId("initMessage"), roomId);
        messages.forEach(message -> {
            String userName = sqlSession.selectOne(sqlId("findUserName"), message.getUserId());
            message.setUserName(userName);
        });
        Collections.reverse(messages);
        return messages;
    }


}
