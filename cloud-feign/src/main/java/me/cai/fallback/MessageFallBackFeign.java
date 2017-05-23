package me.cai.fallback;

import me.cai.model.Message;
import me.cai.response.MyResponse;
import me.cai.service.MessageServiceFeign;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * me.cai.fallback
 *
 * @author caiguangzheng
 * @date 2017/5/23
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class MessageFallBackFeign implements MessageServiceFeign {
    @Override
    public MyResponse<List<Message>> initMessage(Long roomId) {
        return MyResponse.fail("调用MessageService的initMessage失败");
    }
}
