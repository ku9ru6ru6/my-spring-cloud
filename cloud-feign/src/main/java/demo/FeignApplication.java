package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * demo
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * Feign包含了Ribbon,自动实现负载均衡
 * TODO:
 */

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(FeignApplication.class);
        builder.run(args);
    }
}
