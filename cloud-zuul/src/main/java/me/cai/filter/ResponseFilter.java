package me.cai.filter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import me.cai.exception.JsonResponseExceptionView;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * me.cai.filter
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class ResponseFilter extends ZuulFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        InputStream in = RequestContext.getCurrentContext().getResponseDataStream();
        String json = null;
        try {
            json = CharStreams.toString(new InputStreamReader(in, "UTF-8"));
            JsonResponseExceptionView view = objectMapper.readValue(json, new TypeReference<JsonResponseExceptionView>() {});
            if (!Objects.equals(view.getStatus(), 200)) {
                response.sendError(view.getStatus(), view.getMessage());
            }
        } catch (IOException e) {
            try {
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(json);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
