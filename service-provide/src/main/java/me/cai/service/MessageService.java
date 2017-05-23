package me.cai.service;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import me.cai.dao.MessageDao;
import me.cai.model.Message;
import me.cai.response.MyResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/5/23
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@RestController
@Slf4j
@RequestMapping("/service/message")
public class MessageService {

    @Resource
    private MessageDao messageDao;


    @GetMapping("/initMessage")
    public MyResponse<List<Message>> initMessage(@RequestParam("roomId") Long roomId) {
        try {
            return MyResponse.ok(messageDao.initMessage(roomId));
        } catch (Exception e) {
            log.error("MessageService initMessage fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("消息初始化失败");
        }
    }
}
