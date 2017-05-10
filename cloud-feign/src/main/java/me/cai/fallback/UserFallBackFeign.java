package me.cai.fallback;

import me.cai.model.User;
import me.cai.response.MyResponse;
import me.cai.service.UserServiceFeign;
import org.springframework.stereotype.Component;

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
    public MyResponse<Long> createUser(User user) {
        return MyResponse.fail("UserFallBackFeign.call.createUser.fail");
    }

    @Override
    public MyResponse<Boolean> checkName(String name) {
        return MyResponse.fail("UserFallBackFeign.call.checkName.fail");
    }

    @Override
    public MyResponse<User> login(User loginUser) {
        return MyResponse.fail("UserFallBackFeign.call.login.fail");
    }

    @Override
    public MyResponse<String> hello() {
        return MyResponse.fail("UserFallBackFeign.call.hello.hello");
    }

}
