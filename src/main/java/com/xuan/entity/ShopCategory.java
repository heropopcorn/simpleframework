package com.xuan.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCategory {
    //店铺类别id
    private Long shopCategoryId;
    //店铺类别名称
    private String shopCategoryName;
    //店铺类别描述
    private String shopCategoryDesc;
    //店铺类别图片
    private String shopCategoryImg;
    //权重，越大优先级越高
    private Integer priority;
    //创建时间
    private Date createTime;
    //最后一次修改时间
    private Date lastEditTime;
    //店铺类别的父类别
    private ShopCategory parent;
}
