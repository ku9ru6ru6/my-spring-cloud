package me.cai.service;

import me.cai.fallback.RoomFallBackFeign;
import me.cai.model.Room;
import me.cai.response.MyResponse;
import me.cai.response.Paging;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/5/11
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@FeignClient(serviceId = "service-provide", fallback = RoomFallBackFeign.class)
public interface RoomServiceFeign {

    @RequestMapping(value = "/service/room", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    MyResponse<Long> createRoom(@RequestBody Room room);

    @RequestMapping(value = "/service/room/checkName", method = RequestMethod.POST)
    MyResponse<Boolean> checkName(@RequestParam("name") String name);

    @RequestMapping(value = "/service/room/paging", method = RequestMethod.GET)
    MyResponse<Paging<Room>> paging(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageSize", defaultValue = "20", required = false) Integer limit,
                                    @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer offset);
}
