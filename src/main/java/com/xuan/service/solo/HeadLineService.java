package com.xuan.service.solo;

import com.xuan.entity.HeadLine;
import com.xuan.entity.dto.Result;

import java.util.List;


public interface HeadLineService {

    Result<Boolean> add(HeadLine headLine);

    Result<Boolean> remove(HeadLine headLine);

    Result<Boolean> modify(HeadLine headLine);

    Result<HeadLine> query(HeadLine headLine);

    Result<List<HeadLine>> queryList(HeadLine headLine);

    Result<List<HeadLine>> queryPage(HeadLine headLine, int pageNum, int pageSize);
}
