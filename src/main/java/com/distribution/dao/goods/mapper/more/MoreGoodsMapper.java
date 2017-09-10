package com.distribution.dao.goods.mapper.more;

import com.distribution.common.utils.Page;
import com.distribution.dao.goods.model.Goods;

import java.util.List;

public interface MoreGoodsMapper {
    /**
     * description 查询满足条件的会员有多少条
     * @author Bright
     * */
    Integer getGoodsCount(Page page);
    /**
     * description 分页查询
     * @author Bright
     * */
    List<Goods> list(Page page);
}