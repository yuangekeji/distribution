/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.distribution.dao.order.model.OrderMaster;

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
	@Test
	public void buildNodeTreeByNodeTest(){
		int nodeId = 3;
		nodeService.buildNodeTreeByNode(nodeId);
	}
	@Test
	public void processMemberPromotionTest(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(9000));
		order.setId(561);
		order.setCreateId(0);
		order.setOrderNo(new Long("171127013131061"));
		order.setCreateTime(new Date());
		order.setMemberId(382);
		int nodeId = 382;
		nodeService.processMemberPromotion(nodeId, order.getCreateId());
	}
	@Test
	public void processMemberPromotionTest2(){
		nodeService.processMemberPromotion(167,"post_level2","post_level3",3);
	}
	@Test
	public void insertMemberNodeBonus(){
		OrderMaster order = new OrderMaster();
		order.setOrderAmt(new BigDecimal(1200));
		order.setId(8);
		order.setCreateId(8);
		order.setOrderNo(new Long("17012301322900"));
		order.setCreateTime(new Date());
		order.setMemberId(16);
		int nodeId = 571;
		nodeService.insertMemberNodeBonus(nodeId, order);
	}
	@Test
	public void getSubNodeNumberAndSales(){
		Map<String,String> map = nodeService.getSubNodeNumberAndSales(3);
	}
	@Test
	public void getParentNodeTest(){
		MemberNode node = nodeService.getParentNode(3);
		System.out.println(node.getId());
	}
	@Test
	public void saveNodeAutoTest(){
		MemberNode node = new MemberNode();
		node.setParentId(3);
		node.setCreateBy(3);
		MemberNode memberNode = nodeService.saveNode(node);
		System.out.println("============ " + memberNode.getId());
	}
}
