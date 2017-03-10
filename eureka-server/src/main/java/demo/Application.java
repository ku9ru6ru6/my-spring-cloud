package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * demo
 *
 * @author caiguangzheng
 * @date 2017/3/9
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@EnableEurekaServer
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.web(true);
        builder.run(args);
    }
}
