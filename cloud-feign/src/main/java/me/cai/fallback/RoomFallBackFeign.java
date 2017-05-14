package me.cai.fallback;

import me.cai.model.Room;
import me.cai.response.MyResponse;
import me.cai.response.Paging;
import me.cai.service.RoomServiceFeign;
import org.springframework.stereotype.Component;

/**
 * me.cai.fallback
 *
 * @author caiguangzheng
 * @date 2017/5/11
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class RoomFallBackFeign implements RoomServiceFeign {
    @Override
    public MyResponse<Long> createRoom(Room room) {
        return MyResponse.fail("调用RoomService的createRoom服务失败");
    }

    @Override
    public MyResponse<Boolean> checkName(String name) {
        return MyResponse.fail("调用RoomService的checkName服务失败");
    }

    @Override
    public MyResponse<Paging<Room>> paging(String name, Integer limit, Integer offset) {
        return MyResponse.fail("调用RoomService的paging服务失败");
    }
}
