package me.cai.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.cai.exception.JsonResponseException;
import me.cai.exception.JsonResponseExceptionView;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * me.cai.view
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class JsonView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JsonResponseException exception = (JsonResponseException) model.get("exception");
        JsonResponseExceptionView view = new JsonResponseExceptionView(exception);
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(objectMapper.writeValueAsString(view));
    }
}
