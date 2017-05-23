package me.cai.service;

import me.cai.fallback.MessageFallBackFeign;
import me.cai.model.Message;
import me.cai.response.MyResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/5/23
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@FeignClient(serviceId = "service-provide", fallback = MessageFallBackFeign.class)
public interface MessageServiceFeign {

    @RequestMapping(value = "/service/message/initMessage", method = RequestMethod.GET)
    MyResponse<List<Message>> initMessage(@RequestParam("roomId") Long roomId);
}
