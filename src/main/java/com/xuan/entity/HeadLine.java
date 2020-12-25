package com.xuan.entity;

import lombok.Data;

import java.util.Date;

@Data
public class HeadLine {
    //主键id
    private Long lineId;
    //头条名称
    private String lineName;
    //头条链接
    private String lineLink;
    //头条图片
    private String lineImg;
    //权重，越大排名越靠前
    private Integer priority;
    //可用状态：0可用，1不可用
    private Integer enableStatus;
    //创建时间
    private Date createTime;
    //最近一次修改时间
    private Date lastEditTime;
}
