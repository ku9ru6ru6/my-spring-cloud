package me.cai.controller;

import me.cai.model.User;
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
@RequestMapping("/api/user")
public class FeignController {

    private final UserServiceFeign userServiceFeign;

    @Autowired
    public FeignController(UserServiceFeign userServiceFeign) {
        this.userServiceFeign = userServiceFeign;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createUser(@RequestBody User user) {
        Long id = userServiceFeign.createUser(user);
        return id;
    }

    @GetMapping("/hello")
    public String hello() {
        return userServiceFeign.hello();
    }

}
