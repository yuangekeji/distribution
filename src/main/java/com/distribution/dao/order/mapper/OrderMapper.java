package com.distribution.dao.order.mapper;

import com.distribution.dao.order.model.order;
import com.distribution.dao.order.model.orderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface orderMapper {
    int countByExample(orderExample example);

    int deleteByExample(orderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(order record);

    int insertSelective(order record);

    List<order> selectByExample(orderExample example);

    order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") order record, @Param("example") orderExample example);

    int updateByExample(@Param("record") order record, @Param("example") orderExample example);

    int updateByPrimaryKeySelective(order record);

    int updateByPrimaryKey(order record);
}