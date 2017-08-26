package com.distribution.dao.goods.mapper;

import com.distribution.dao.goods.model.goods;
import com.distribution.dao.goods.model.goodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface goodsMapper {
    int countByExample(goodsExample example);

    int deleteByExample(goodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(goods record);

    int insertSelective(goods record);

    List<goods> selectByExample(goodsExample example);

    goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") goods record, @Param("example") goodsExample example);

    int updateByExample(@Param("record") goods record, @Param("example") goodsExample example);

    int updateByPrimaryKeySelective(goods record);

    int updateByPrimaryKey(goods record);
}