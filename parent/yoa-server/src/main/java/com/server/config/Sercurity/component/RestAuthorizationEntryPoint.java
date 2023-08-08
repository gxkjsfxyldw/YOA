package com.server.config.Sercurity.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.vo.common.RespBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录或者token失效时访问接口时，自定义的返回结果
 */
//@Conponent 修饰的类不会被代理，每实例化一次就会创建一个新的对象
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        //通过这个 PrintWriter，可以将文本或其他数据发送给客户端
        PrintWriter out=httpServletResponse.getWriter();
        RespBean bean=RespBean.error("未登录，请先登录！");
        bean.setCode(406);
        out.write(new ObjectMapper().writeValueAsString(bean));// Java 对象 bean 序列化为 JSON 字符串
        out.flush();//这是将缓冲区的内容强制刷新到底层流中，以确保数据被发送到客户端
        out.close();//这是关闭输出流，释放资源。在关闭流之前，需要先刷新缓冲区，以确保所有数据都被正确发送
    }
}
