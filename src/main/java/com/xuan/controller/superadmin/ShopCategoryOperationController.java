package com.xuan.controller.superadmin;

import com.xuan.entity.HeadLine;
import com.xuan.entity.ShopCategory;
import com.xuan.entity.dto.Result;
import com.xuan.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ShopCategoryOperationController {

    @Autowired
    private ShopCategoryService shopCategoryService;


    public Result<Boolean> add(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.add(new ShopCategory());
    }

    public Result<Boolean> remove(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.remove(new ShopCategory());
    }

    public Result<Boolean> modify(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.modify(new ShopCategory());
    }

    public Result<ShopCategory> query(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.query(new ShopCategory());
    }

    public Result<List<ShopCategory>> queryList(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.queryList(new ShopCategory());
    }

    public Result<List<ShopCategory>> queryPage(HttpServletRequest req, HttpServletResponse resp) {
        //TODO 请求参数校验和参数转化
        return shopCategoryService.queryPage(new ShopCategory(), 1, 100);
    }
}
