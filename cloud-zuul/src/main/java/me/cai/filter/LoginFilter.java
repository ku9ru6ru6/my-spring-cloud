package me.cai.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * me.cai.filter
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO: 登录验证
 */
@Component
public class LoginFilter extends ZuulFilter {

    private static final String FILTER_TYPE = "pre";
    private static final String LOGIN_PAGE = "http://localhost:9004/login.html";

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("userId"))) {
            try {
                response.sendRedirect(LOGIN_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
