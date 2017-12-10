package com.distribution.dao.memberNode.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.memberNode.mapper.MemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;

public interface MoreMemberNodeMapper extends MemberNodeMapper{
	int insertBackId(MemberNode record);
	List<MemberNode> findParentNodes(Map<String,Object> map);
	Double findTotalSalesByParentId(Map<String,Object> map);
	Double findTotalSalesByParentIdNotIncludeCurrentNode(Map<String,Object> map);
	int updateParentLevel(Map<String,Object> map);
	List<MoreMemberNode> listParentNodesWhichHasTwoSubNodes(Map<String,Object> map);
	List<MoreMemberNode> listParentIsManageLevelNodes(Map<String,Object> map);
	List<MoreMemberNode> listSubNodes(Map<String,Object> map);
	List<MoreMemberNode> listParentNodesWithMemberInfo(Map<String,Object> map);
	Map<String,Object> getSubNodesIsSalesDept(int nodeId);
	List<MemberNode> listSubNodesByRecommendNode(Map<String,Object> map);
	String getSubNodes(int nodeId);
	String getParentNodes(int nodeId);
	MemberNode getMemberNodeByMemberId(Integer MemberId);
	int updateRemoveMemberNode(Map map);
	int countRecommendedTotal(int memberId);
}