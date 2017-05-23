package me.cai.dao;

import me.cai.model.Message;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * me.cai.dao
 *
 * @author caiguangzheng
 * @date 2017/5/23
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Repository
public class MessageDao {

    @Resource
    protected SqlSession sqlSession;

    private String sqlId(String methodName) {
        return "me.cai.model.Message." + methodName;
    }

    public List<Message> initMessage(Long roomId) {
        List<Message> messages = sqlSession.selectList(sqlId("initMessage"), roomId);
        messages.forEach(message -> {
            String userName = sqlSession.selectOne(sqlId("findUserName"), message.getUserId());
            message.setUserName(userName);
        });
        Collections.reverse(messages);
        return messages;
    }
}
