package cn.edu.svtcc.filter;

import cn.edu.svtcc.common.domain.AjaxResult;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@WebFilter(filterName ="loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;

        String uri=req.getRequestURI();

        String[] urls={
                "/*/user/login",
                "/*/user/logout",
                "/*/captchaImage",
                "/*/admin/**",
                "/*/js/**",
                "/*/api/**",
                "/*/images/**",
                "/*/plugins/**",
                "/*/styles/**",
                "/*/login.html",
        };
        log.info(uri);
        boolean check=check(urls,uri);
        // 检查是否需要放行
        if(check){
            filterChain.doFilter(req,resp);
            return;
        }

        if(req.getSession().getAttribute("User")!=null){
            filterChain.doFilter(req,resp);
            return;
        }

        AjaxResult ajax=AjaxResult.error("NOTLOGIN");
        log.info(ajax.toString());
        resp.getWriter().write(JSON.toJSONString(ajax));
    }

    public boolean check(String[] urls,String uri){
        AntPathMatcher pathMatcher=new AntPathMatcher();
        for(String url:urls){
            boolean match = pathMatcher.match(url, uri);
            if(match){
                return true;
            }
        }
        return false;
    }
}
