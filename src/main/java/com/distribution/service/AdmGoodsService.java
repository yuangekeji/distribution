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
    public Integer insertOrUpdateGoods(Goods goods){
        if(null==goods.getId()) {
            goods.setListTime(new Date());
            goods.setCreateTime(new Date());
            goods.setDeleteFlag("N");
            goods.setStatus("N");
            return goodsMapper.insert(goods);
        }else{
            return goodsMapper.updateByPrimaryKeySelective(goods);
        }
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

    /**
     * description 修改上架下架
     * @author Bright
     * */
    public Integer handle(Goods goods){
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * description 根据ID查询物品
     * @author Bright
     * */
    public Goods getGoodsById(Integer id){
        return goodsMapper.selectByPrimaryKey(id);
    }
}
