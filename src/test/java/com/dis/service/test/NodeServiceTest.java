/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.service.NodeService;

public class NodeServiceTest extends SpringServiceTest{
	private static Logger log = Logger.getLogger(NodeServiceTest.class);
	@Autowired
	private NodeService nodeService;
	/*@Autowired
	public MenuService menuService;
	@Test
	public void testRoleMenu(){
		List<Menu> list = menuService.getMenuByRoleId("1");
		for(Menu menu:list){
			log.debug("menu name is aaa "  + menu.getName());
		}
	}*/
	@Test
	public void saveNodeTest(){
		MemberNode node = new MemberNode();
		node.setCreateBy(2);
		node.setCreateTime(new Date());
		node.setParentId(2);
		nodeService.saveNode(node, "right");
	}
	@Test
	public void findNodeTest(){
		int result = nodeService.findNodeByParentNode(2, "right");
		log.debug("========= result is " + result);
	}
}
