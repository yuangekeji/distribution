package com.distribution.dao.orderDetail.mapper;

import com.distribution.dao.orderDetail.model.orderDetail;
import com.distribution.dao.orderDetail.model.orderDetailExample;
import com.distribution.dao.orderDetail.model.orderDetailKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface orderDetailMapper {
    int countByExample(orderDetailExample example);

    int deleteByExample(orderDetailExample example);

    int deleteByPrimaryKey(orderDetailKey key);

    int insert(orderDetail record);

    int insertSelective(orderDetail record);

    List<orderDetail> selectByExample(orderDetailExample example);

    orderDetail selectByPrimaryKey(orderDetailKey key);

    int updateByExampleSelective(@Param("record") orderDetail record, @Param("example") orderDetailExample example);

    int updateByExample(@Param("record") orderDetail record, @Param("example") orderDetailExample example);

    int updateByPrimaryKeySelective(orderDetail record);

    int updateByPrimaryKey(orderDetail record);
}