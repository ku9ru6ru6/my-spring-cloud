package me.cai.service;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import me.cai.dao.UserDao;
import me.cai.model.User;
import me.cai.response.MyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * demo.service
 *
 * @author caiguangzheng
 * @date 2017/3/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */

@RestController
@Slf4j
@RequestMapping("/service/user")
public class UserService {

    private MessageDigest md5;

    @Resource
    private UserDao userDao;

    @PostConstruct
    public void init() {
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MyResponse<Long> createUser(@RequestBody User user) {
        try {
            user.setPassword(encryption(user.getPassword()));
            userDao.createUser(user);
            return MyResponse.ok(user.getId());
        } catch (Exception e) {
            log.error("UserService createUser fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("创建用户失败");
        }
    }

    @PostMapping("/checkName")
    public MyResponse<Boolean> checkName(@RequestParam("name") String name) {
        try {
            Boolean result = userDao.checkName(name);
            return MyResponse.ok(result);
        } catch (Exception e) {
            log.error("UserService checkName fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("检查用户名唯一性失败");
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public MyResponse<User> login(@RequestBody User loginUser) {
        try {
            User dbUser = userDao.findByName(loginUser.getName());
            if (Objects.equals(dbUser.getPassword(), encryption(loginUser.getPassword()))) {
                return MyResponse.ok(dbUser);
            } else {
                return MyResponse.fail("密码错误");
            }
        } catch (Exception e) {
            log.error("UserService login fail, error:{}", Throwables.getStackTraceAsString(e));
            return MyResponse.fail("登录验证服务出错");
        }
    }

    @GetMapping("/hello")
    public MyResponse<String> hello() throws IOException {
        return MyResponse.fail("Hello 服务出错");
    }

    /**
     * md5 加密
     *
     * @param password 密码
     * @return md5的值
     */
    private String encryption(String password) {
        byte[] cipherData = md5.digest(password.getBytes());
        StringBuilder builder = new StringBuilder();
        for(byte cipher : cipherData) {
            String toHexStr = Integer.toHexString(cipher & 0xff);
            builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
        }
        return builder.toString();
    }
}
