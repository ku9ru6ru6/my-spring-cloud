package me.cai.dao;

import me.cai.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * me.cai.dao
 *
 * @author caiguangzheng
 * @date 2017/4/17
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Repository
public class UserDao {

    @Resource
    private SqlSession sqlSession;

    private final String sqlId(String methodName) {
        return "me.cai.model.User." + methodName;
    }

    public Boolean createUser(User user) {
        return sqlSession.insert(sqlId("createUser"), user) > 0;
    }

    public Integer checkName(String name) {
        return sqlSession.selectOne(sqlId("checkName"), name);
    }

    public User findByName(String name) {
        return sqlSession.selectOne(sqlId("findByName"), name);
    }
}
