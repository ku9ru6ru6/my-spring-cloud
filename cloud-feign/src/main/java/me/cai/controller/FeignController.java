package me.cai.controller;

import lombok.extern.slf4j.Slf4j;
import me.cai.exception.JsonResponseException;
import me.cai.model.User;
import me.cai.response.MyResponse;
import me.cai.service.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * demo.controller
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@RestController
@Slf4j
@RequestMapping("/api/user")
public class FeignController {

    private final UserServiceFeign userServiceFeign;

    @Autowired
    public FeignController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createUser(@RequestBody User user) {
        MyResponse<Long> result = userServiceFeign.createUser(user);
        if (!result.isSuccess()) {
            log.error("FeignController createUser fail, error:{}", result.getError());
            throw new JsonResponseException(result.getCode(), result.getError());
        }
        return result.getResult();
    }

    @GetMapping("/hello")
    public String hello() {
        MyResponse<String> result = userServiceFeign.hello();
        if (!result.isSuccess()) {
            log.error("FeignController hello fail, error:{}", result.getError());
            throw new JsonResponseException(result.getCode(), result.getError());
        }
        return result.getResult();
    }

}
