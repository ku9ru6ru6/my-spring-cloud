package me.cai.handle;

import com.google.common.collect.ImmutableMap;
import me.cai.exception.JsonResponseException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * me.cai.handel
 *
 * @author caiguangzheng
 * @date 2017/5/10
 * Mail: caiguangzheng@terminus.io
 * TODO:
 */
@Component
public class JsonResponseExceptionHandle implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof JsonResponseException) {
            return new ModelAndView("jsonView", ImmutableMap.of("exception", ex));
        } else return null;
    }
}
