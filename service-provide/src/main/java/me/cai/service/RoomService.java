package me.cai.service;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import me.cai.dao.RoomDao;
import me.cai.model.Room;
import me.cai.response.MyResponse;
import me.cai.response.Paging;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@RestController
@Slf4j
@RequestMapping("/service/room")
public class RoomService {

    @Resource
    private RoomDao roomDao;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MyResponse<Long> createRoom(@RequestBody Room room) {
        try {
            roomDao.createRoom(room);
            return MyResponse.ok(room.getId());
        } catch (Exception e) {
            log.error("RoomService createRoom fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("创建房间失败");
        }
    }

    @PostMapping("/checkName")
    public MyResponse<Boolean> checkName(@RequestParam("name") String name) {
        try {
            Integer result = roomDao.checkName(name);
            return MyResponse.ok(Objects.isNull(result));
        } catch (Exception e) {
            log.error("RoomService checkName fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("检查房间名唯一性失败");
        }
    }

    @GetMapping("/paging")
    public MyResponse<Paging<Room>> paging(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer limit,
                                           @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer offset) {
        try {
            Long total = roomDao.count(name);
            Map<String, Object> map = new HashMap<>(3);
            map.put("name", name);
            map.put("limit", limit);
            map.put("offset", offset);
            List<Room> data = roomDao.paging(map);
            return MyResponse.ok(new Paging<>(total, data));
        } catch (Exception e) {
            log.error("RoomService paging fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("获取房间分页失败");
        }
    }
}
