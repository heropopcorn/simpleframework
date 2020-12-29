package com.xuan.controller;

import com.xuan.controller.frontend.MainPageController;
import com.xuan.controller.superadmin.HeadLineOperationController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherSevlet
 *
 * @WebServlet("/")表示拦截所有请求,而tomcat中conf下的web.xml中也预设了两种有默认预制匹配： "/"和“*.jsp”
 * "/"表示匹配所有路径，但是优先级会低于后缀匹配“*.jsp”，
 * “/*”也表示匹配所有路径，但是其优先级高于后缀匹配，
 * 所以如果我们自己的servlet配置“/”，对于转发到jsp不会有问题，但是如果配置了“/*”，后缀匹配就会被覆盖，
 * 那么转发之后访问jsp的操作也会被自己拦截，从而形成死循环
 * 所以servlet拦截路径的配置需要根据优先级合理配置以避免在访问时形成死循环
 * @Author xuan
 * @Date 2020/12/28 10:25
 */

@WebServlet("/")
public class DispatcherSevlet extends HttpServlet {

    /**
     * 重写service方法，按照自己的逻辑实现后续调用顺序
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        System.out.println(servletPath);
        String method = req.getMethod();
        System.out.println(method);
        if ("/frontend/getmainpageinfo".endsWith(servletPath) && "GET".endsWith(method)) {
            new MainPageController().getMainPageInfo(req, resp);
        } else if ("/superadmin/addheadline".endsWith(servletPath) && "POST".endsWith(method)) {
            new HeadLineOperationController().add(req, resp);
        }

    }
}
