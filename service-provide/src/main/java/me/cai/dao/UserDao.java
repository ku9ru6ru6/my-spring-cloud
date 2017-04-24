package me.cai.dao;

import com.google.common.collect.ImmutableMap;
import me.cai.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    private final SqlSession sqlSession;

    private final String sqlId(String methodName) {
        return "me.cai.model.User." + methodName;
    }

    @Autowired
    public UserDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Boolean createUser(User user) {
        return sqlSession.insert(sqlId("createUser"), user) > 0;
    }

    public Integer checkName(String name) {
        return sqlSession.selectOne(sqlId("checkName"), ImmutableMap.of("name", name));
    }

    public User findByName(String name) {
        return sqlSession.selectOne(sqlId("findByName"), ImmutableMap.of("name", name));
    }
}
