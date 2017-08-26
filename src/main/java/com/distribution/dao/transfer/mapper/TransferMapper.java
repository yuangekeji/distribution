package com.distribution.dao.transfer.mapper;

import com.distribution.dao.transfer.model.Transfer;
import com.distribution.dao.transfer.model.TransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransferMapper {
    int countByExample(TransferExample example);

    int deleteByExample(TransferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Transfer record);

    int insertSelective(Transfer record);

    List<Transfer> selectByExample(TransferExample example);

    Transfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Transfer record, @Param("example") TransferExample example);

    int updateByExample(@Param("record") Transfer record, @Param("example") TransferExample example);

    int updateByPrimaryKeySelective(Transfer record);

    int updateByPrimaryKey(Transfer record);
}