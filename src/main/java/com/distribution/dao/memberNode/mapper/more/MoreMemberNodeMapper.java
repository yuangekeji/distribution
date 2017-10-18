package com.distribution.dao.memberNode.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.memberNode.mapper.MemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;

public interface MoreMemberNodeMapper extends MemberNodeMapper{
	int insertBackId(MemberNode record);
	List<MemberNode> findParentNodes(int nodeId);
	Double findTotalSalesByParentId(int parentId);
	Double findTotalSalesByParentIdNotIncludeCurrentNode(int parentId);
	int updateParentLevel(Map<String,Object> map);
	List<MoreMemberNode> listParentNodesWhichHasTwoSubNodes(Map<String,Object> map);
	List<MoreMemberNode> listParentIsManageLevelNodes(int nodeId);
	List<MoreMemberNode> listSubNodes(int nodeId);
	List<MoreMemberNode> listParentNodesWithMemberInfo(int nodeId);
	Map<String,Object> getSubNodesIsSalesDept(int nodeId);
	List<MemberNode> listSubNodesByRecommendNode(int nodeId);
}