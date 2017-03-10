package demo.server;

import model.People;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * demo.server
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@Service
public class FeignControllerFallBack implements ComputerServer {

    @Override
    public Integer add(@RequestParam(name = "a") Integer a, @RequestParam(name = "b") Integer b) {
        return -1;
    }

    @Override
    public People getPeople(@RequestParam(name = "name") String name, @RequestParam(name = "age") Integer age) {
        return null;
    }

    @Override
    public String test() {
        return null;
    }
}
