package com.distribution.dao.memberNode.mapper.more;

import java.util.List;
import java.util.Map;

import com.distribution.dao.memberNode.mapper.MemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.memberNode.model.more.MoreMemberNode;

public interface MoreMemberNodeMapper extends MemberNodeMapper{
	int insertBackId(MemberNode record);
	List<MemberNode> findParentNodes(int nodeId);
	double findTotalSalesByParentId(int parentId);
	int updateParentLevel(Map<String,String> map);
	List<MoreMemberNode> listParentNodesWhichHasTwoSubNodes(Map<String,String> map);
	List<MoreMemberNode> listParentIsManageLevelNodes(int nodeId);
	List<MoreMemberNode> listSubNodes(int nodeId);
	List<MoreMemberNode> listParentNodesWithMemberInfo(int nodeId);
}