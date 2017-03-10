package demo.server;

import model.People;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * demo.server
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@FeignClient(value = "server-provide", fallback = FeignControllerFallBack.class)
public interface ComputerServer {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    Integer add(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b);

    @RequestMapping(value = "/getPeople", method = RequestMethod.GET)
    People getPeople(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age);

    @RequestMapping(value = "/test")
    String test();
}
