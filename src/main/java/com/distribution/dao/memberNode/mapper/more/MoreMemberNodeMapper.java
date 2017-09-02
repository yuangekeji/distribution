package com.distribution.dao.memberNode.mapper.more;

import com.distribution.dao.memberNode.mapper.MemberNodeMapper;
import com.distribution.dao.memberNode.model.MemberNode;

public interface MoreMemberNodeMapper extends MemberNodeMapper{
	int insertBackId(MemberNode record);
}