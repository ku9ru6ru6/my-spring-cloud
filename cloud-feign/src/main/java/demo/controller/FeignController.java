package demo.controller;

import demo.server.ComputerServer;
import model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo.controller
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@RestController
public class FeignController {

    @Autowired
    private ComputerServer computerServer;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b) {
        return computerServer.add(a, b);
    }

    @RequestMapping(value = "/getPeople", method = RequestMethod.GET)
    public People getPeople(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age) {
        People people = computerServer.getPeople(name, age);
        return people;
    }

    @RequestMapping(value = "/true/test")
    public String test() {
        return computerServer.test();
    }
}
