package com.distribution.dao.nodeBonusHistory.mapper;

import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistory;
import com.distribution.dao.nodeBonusHistory.model.NodeBonusHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NodeBonusHistoryMapper {
    int countByExample(NodeBonusHistoryExample example);

    int deleteByExample(NodeBonusHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NodeBonusHistory record);

    int insertSelective(NodeBonusHistory record);

    List<NodeBonusHistory> selectByExample(NodeBonusHistoryExample example);

    NodeBonusHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NodeBonusHistory record, @Param("example") NodeBonusHistoryExample example);

    int updateByExample(@Param("record") NodeBonusHistory record, @Param("example") NodeBonusHistoryExample example);

    int updateByPrimaryKeySelective(NodeBonusHistory record);

    int updateByPrimaryKey(NodeBonusHistory record);
}