package me.cai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * me.cai
 *
 * @author caiguangzheng
 * @date 2017/4/24
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@SpringBootApplication
@EnableRedisHttpSession
public class ChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }
}
