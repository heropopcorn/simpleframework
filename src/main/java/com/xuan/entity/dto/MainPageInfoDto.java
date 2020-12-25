package com.xuan.entity.dto;

import com.xuan.entity.HeadLine;
import com.xuan.entity.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDto {
    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;
}
