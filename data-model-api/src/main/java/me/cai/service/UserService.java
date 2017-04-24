package me.cai.service;

import me.cai.model.User;

/**
 * me.cai.service
 *
 * @author caiguangzheng
 * @date 2017/4/19
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
public interface UserService {

    Long createUser(User user);

    Integer checkName(String name);

    User login(User loginUser);

    String hello();
}
