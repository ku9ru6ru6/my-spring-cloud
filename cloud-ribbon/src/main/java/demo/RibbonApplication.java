package demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
public class RibbonApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RibbonApplication.class);
        builder.run(args);
    }
}
