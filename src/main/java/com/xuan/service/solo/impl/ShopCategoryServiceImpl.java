package com.xuan.service.solo.impl;

import com.xuan.entity.ShopCategory;
import com.xuan.entity.dto.Result;
import com.xuan.service.solo.ShopCategoryService;
import org.simpleframework.core.annotation.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Override
    public Result<Boolean> add(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> remove(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<Boolean> modify(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<ShopCategory> query(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryList(ShopCategory shopCategory) {
        return null;
    }

    @Override
    public Result<List<ShopCategory>> queryPage(ShopCategory shopCategory, int pageNum, int pageSize) {
        return null;
    }
}
