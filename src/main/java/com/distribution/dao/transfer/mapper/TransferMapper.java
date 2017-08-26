package com.distribution.dao.transfer.mapper;

import com.distribution.dao.transfer.model.transfer;
import com.distribution.dao.transfer.model.transferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface transferMapper {
    int countByExample(transferExample example);

    int deleteByExample(transferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(transfer record);

    int insertSelective(transfer record);

    List<transfer> selectByExample(transferExample example);

    transfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") transfer record, @Param("example") transferExample example);

    int updateByExample(@Param("record") transfer record, @Param("example") transferExample example);

    int updateByPrimaryKeySelective(transfer record);

    int updateByPrimaryKey(transfer record);
}