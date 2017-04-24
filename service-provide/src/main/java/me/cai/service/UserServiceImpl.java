package me.cai.service;

import me.cai.dao.UserDao;
import me.cai.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
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
@RequestMapping("/service/user")
public class UserServiceImpl implements UserService {

    private MessageDigest md5;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void init() {
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createUser(@RequestBody User user) {
        user.setPassword(encryption(user.getPassword()));
        userDao.createUser(user);
        return user.getId();
    }

    @Override
    @GetMapping("/checkName/{name}")
    public Integer checkName(@PathVariable("name") String name) {
        return userDao.checkName(name);
    }

    @Override
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody User loginUser) {
        User dbUser = userDao.findByName(loginUser.getName());
        if (Objects.equals(dbUser.getPassword(), encryption(loginUser.getPassword()))) {
            return dbUser;
        } else {
            return null;
        }
    }

    @Override
    @GetMapping("/hello")
    public String hello() {
        return "hello";
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
