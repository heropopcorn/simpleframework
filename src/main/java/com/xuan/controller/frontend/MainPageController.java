package com.xuan.controller.frontend;

import com.xuan.entity.dto.MainPageInfoDto;
import com.xuan.entity.dto.Result;
import com.xuan.service.combine.HeadLineShopCategoryCombineService;
import lombok.Getter;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.inject.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Getter
public class MainPageController {

    @Autowired("HeadLineShopCategoryCombineServiceImpl")
    private HeadLineShopCategoryCombineService headLineShopCategoryCombineService;

    public Result<MainPageInfoDto> getMainPageInfo(HttpServletRequest req, HttpServletResponse resp) {
        return headLineShopCategoryCombineService.getMainPageInfo();
    }
}
