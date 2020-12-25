package com.xuan;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 从servlet3.0之后可以使用注解的方式使用servlet，不需要在web.xml文件中做配置
 * HelloServlet
 *
 * @Author xuan
 * @Date 2020/12/22 15:39
 */
@Slf4j
@WebServlet("/hello")//如果需要访问此servlet,需要ip:端口号/项目名称/servlet路径，即：localhost:8080/simpleframework/hello
public class HelloServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = "简易框架";
        log.debug("name is {}", name);
        req.setAttribute("name", name);
        //转发
        req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
