package me.cai.fallback;

import me.cai.model.User;
import me.cai.service.UserServiceFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * me.cai.fallback
 *
 * @author caiguangzheng
 * @date 2017/4/19
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class UserFallBackFeign implements UserServiceFeign {

    @Override
    public Long createUser(User user) {
        return null;
    }

    @Override
    public Integer checkName(String name) {
        return null;
    }

    @Override
    public User login(User loginUser) {
        return null;
    }

    @Override
    public String hello() {
        return "hello error";
    }

}
