package com.distribution.service;

import com.distribution.common.utils.Page;
import com.distribution.dao.goods.mapper.GoodsMapper;
import com.distribution.dao.goods.mapper.more.MoreGoodsMapper;
import com.distribution.dao.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Service
public class GoodsService {


    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private MoreGoodsMapper moreGoodsMapper;

    /**
     * 查询商品详情
     * @param
     * @return
     */
    public Goods goodDetails(Integer id){
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        return goods;
    }
}
