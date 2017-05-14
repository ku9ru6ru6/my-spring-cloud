package me.cai.dao;

import me.cai.model.Room;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    protected SqlSession sqlSession;

    private final String sqlId(String methodName) {
        return "me.cai.model.Room." + methodName;
    }

    public Boolean createRoom(Room room) {
        return sqlSession.insert(sqlId("createRoom"), room) > 0;
    }

    public Boolean checkName(String name) {
        return sqlSession.selectOne(sqlId("checkName"), name);
    }

    public Long count(String name) {
        return sqlSession.selectOne(sqlId("count"), name);
    }

    public List<Room> paging(Map map) {
        return sqlSession.selectList(sqlId("paging"), map);
    }

}
