package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * demo.controller
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/add")
    public String add(@RequestParam Integer a, @RequestParam Integer b) {
        ResponseEntity<String> result = restTemplate.getForEntity("http://computer-service/add?a=" + a + "&b=" + b, String.class);
        System.out.println("get result " + result.getBody());
        return result.getBody();
    }

    @RequestMapping("/getPeople")
    public String getPeople() {
        ResponseEntity<String> result = restTemplate.getForEntity("http://computer-service/getPeople?name=cai&age=23", String.class);
        System.out.println("result body" + result.getBody());
        System.out.println("result -> " + result);
        return result.getBody();
    }
}
