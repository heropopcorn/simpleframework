package com.xuan.service.solo;

import com.xuan.entity.HeadLine;
import com.xuan.entity.ShopCategory;
import com.xuan.entity.dto.Result;

import java.util.List;


/**
 * 店铺类被服务类
 *
 * @Author xuan
 * @Date 2020/12/25 15:11
 */
public interface ShopCategoryService {
    Result<Boolean> add(ShopCategory shopCategory);

    Result<Boolean> remove(ShopCategory shopCategory);

    Result<Boolean> modify(ShopCategory shopCategory);

    Result<ShopCategory> query(ShopCategory shopCategory);

    Result<List<ShopCategory>> queryList(ShopCategory shopCategory);

    Result<List<ShopCategory>> queryPage(ShopCategory shopCategory, int pageNum, int pageSize);
}
