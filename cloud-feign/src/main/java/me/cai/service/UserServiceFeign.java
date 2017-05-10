package me.cai.service;

import me.cai.fallback.UserFallBackFeign;
import me.cai.model.User;
import me.cai.response.MyResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/4/19
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@FeignClient(serviceId = "service-provide", fallback = UserFallBackFeign.class)
public interface UserServiceFeign {

    @RequestMapping(value ="/service/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    MyResponse<Long> createUser(@RequestBody User user);

    @RequestMapping(value = "/service/user/checkName/{name}", method = RequestMethod.GET)
    MyResponse<Boolean> checkName(@PathVariable("name") String name);

    @RequestMapping(value = "/service/user/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    MyResponse<User> login(@RequestBody User loginUser);

    @RequestMapping(value = "/service/user/hello", method = RequestMethod.GET)
    MyResponse<String> hello();

}
