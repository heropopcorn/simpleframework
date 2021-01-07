package com.xuan.service.combine.impl;

import com.xuan.entity.HeadLine;
import com.xuan.entity.ShopCategory;
import com.xuan.entity.dto.MainPageInfoDto;
import com.xuan.entity.dto.Result;
import com.xuan.service.combine.HeadLineShopCategoryCombineService;
import com.xuan.service.solo.HeadLineService;
import com.xuan.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.inject.annotation.Autowired;

import java.util.List;

@Service
public class HeadLineShopCategoryCombineServiceImpl2 implements HeadLineShopCategoryCombineService {

    @Autowired
    private HeadLineService headLineService;
    @Autowired
    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDto> getMainPageInfo() {
        //获取头条列表
        HeadLine headLineWrap = new HeadLine();
        headLineWrap.setEnableStatus(0);
        Result<List<HeadLine>> headLineResult = headLineService.queryPage(headLineWrap, 1, 10);
        //获取店铺类别列表
        ShopCategory shopCategoryWrap = new ShopCategory();
        Result<List<ShopCategory>> shopCategoryResult = shopCategoryService.queryPage(shopCategoryWrap, 1, 100);
        //组合，然后返回
        Result<MainPageInfoDto> result = mergeMainPageInfoResult(headLineResult, shopCategoryResult);
        return result;
    }

    /**
     * 合并操作
     * @param headLineResult
     * @param shopCategoryResult
     * @return
     */
    private Result<MainPageInfoDto> mergeMainPageInfoResult(Result<List<HeadLine>> headLineResult, Result<List<ShopCategory>> shopCategoryResult) {
        return null;
    }
}
