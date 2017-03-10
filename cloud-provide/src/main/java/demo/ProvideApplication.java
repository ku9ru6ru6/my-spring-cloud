package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * demo
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@EnableDiscoveryClient
@SpringBootApplication
public class ProvideApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(ProvideApplication.class);
        builder.run(args);
    }
}
