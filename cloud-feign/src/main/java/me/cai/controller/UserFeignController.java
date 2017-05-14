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
public class UserFeignController {

    private final UserServiceFeign userServiceFeign;

    @Autowired
    public UserFeignController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createUser(@RequestBody User user) {
        MyResponse<Long> result = userServiceFeign.createUser(user);
        if (!result.isSuccess()) {
            log.error("UserFeignController createUser fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody User loginUser) {
        MyResponse<User> result = userServiceFeign.login(loginUser);
        if (!result.isSuccess()) {
            log.error("UserFeignController login fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }

    @PostMapping("/hello")
    public String hello() {
        MyResponse<String> result = userServiceFeign.hello();
        if (!result.isSuccess()) {
            log.error("FeignController hello fail, error:{}", result.getError());
            throw new JsonResponseException(result.getError());
        }
        return result.getResult();
    }

}
