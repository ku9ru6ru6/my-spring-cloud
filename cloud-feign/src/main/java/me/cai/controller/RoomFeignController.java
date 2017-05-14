package me.cai.controller;

import lombok.extern.slf4j.Slf4j;
import me.cai.exception.JsonResponseException;
import me.cai.model.Room;
import me.cai.response.MyResponse;
import me.cai.response.Paging;
import me.cai.service.RoomServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * me.cai.controller
 *
 * @author caiguangzheng
 * @date 2017/5/12
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@RestController
@Slf4j
@RequestMapping("/api/room")
public class RoomFeignController {

    private final RoomServiceFeign roomServiceFeign;

    @Autowired
    public RoomFeignController(RoomServiceFeign roomServiceFeign) {
        this.roomServiceFeign = roomServiceFeign;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createRoom(@RequestBody Room room) {
        MyResponse<Long> result = roomServiceFeign.createRoom(room);
        if (!result.isSuccess()) {
            log.error("RoomFeignController createRoom fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }

    @PostMapping("/checkName")
    public Boolean checkName(@RequestParam("name") String name) {
        MyResponse<Boolean> result = roomServiceFeign.checkName(name);
        if (!result.isSuccess()) {
            log.error("RoomFeignController checkName fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }

    @GetMapping("/paging")
    public Paging<Room> paging(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer limit,
                               @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer offset) {
        MyResponse<Paging<Room>> result = roomServiceFeign.paging(name, limit, offset);
        if (!result.isSuccess()) {
            log.error("RoomFeignController paging fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }
}
