package com.xuan.controller.superadmin;

import com.xuan.entity.HeadLine;
import com.xuan.entity.dto.Result;
import com.xuan.service.solo.HeadLineService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Controller
public class HeadLineOperationController {

    @Autowired
    private HeadLineService headLineService;


    public Result<Boolean> add(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.add(new HeadLine());
    }

    public Result<Boolean> remove(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.remove(new HeadLine());
    }

    public Result<Boolean> modify(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.modify(new HeadLine());
    }

    public Result<HeadLine> query(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.query(new HeadLine());
    }

    public Result<List<HeadLine>> queryList(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.queryList(new HeadLine());
    }

    public Result<List<HeadLine>> queryPage(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return headLineService.queryPage(new HeadLine(), 1, 100);
    }
}
