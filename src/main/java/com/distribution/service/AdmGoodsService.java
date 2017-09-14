package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.goods.mapper.GoodsMapper;
import com.distribution.dao.goods.mapper.more.MoreGoodsMapper;
import com.distribution.dao.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdmGoodsService {


    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private MoreGoodsMapper moreGoodsMapper;

    /**
     * description 添加商品
     * @author Bright
     * */
    public Integer insertGoods(Goods goods){
        goods.setListTime(new Date());
        goods.setCreateTime(new Date());
        goods.setDeleteFlag("N");
        return goodsMapper.insert(goods);
    }

    /**
     * description 商品列表查询
     * @author Bright
     * */
    public Page findList(Page page){
        page.setTotalCount(moreGoodsMapper.getGoodsCount(page));
        page.setResult(moreGoodsMapper.list(page));
        return page;
    }
}
