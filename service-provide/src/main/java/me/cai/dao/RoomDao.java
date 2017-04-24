package me.cai.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * me.cai.dao
 *
 * @author caiguangzheng
 * @date 2017/4/16
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Repository
public class RoomDao {

    @Autowired
    protected SqlSession sqlSession;

    private final String sqlId(String methodName) {
        return "me.cai.model.Room" + methodName;
    }
}
