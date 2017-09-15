package com.distribution.dao.basicManage.mapper.more;

import java.util.List;

import com.distribution.dao.basicManage.mapper.BasicManageMapper;
import com.distribution.dao.basicManage.model.BasicManage;

public interface MoreBasicManageMapper extends BasicManageMapper{
	List<BasicManage> listAll();

    int updateBasicSetting(BasicManage basicManage);
}