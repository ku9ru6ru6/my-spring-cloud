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
    public MyResponse<User> createUser(User user) {
        return MyResponse.fail("调用UserService的createUser服务失败");
    }

    @Override
    public MyResponse<User> login(User loginUser) {
        return MyResponse.fail("调用UserService的login服务失败");
    }

    @Override
    public MyResponse<String> hello() {
        return MyResponse.fail("调用UserService的hello服务失败");
    }

}
