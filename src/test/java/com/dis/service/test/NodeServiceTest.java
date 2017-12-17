/*
  * Pactera Technology International Ltd.
  * Copyright (c) 2014-2015 All Rights Reserved.
  */
package com.dis.service.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dis.junit.test.SpringServiceTest;
import com.distribution.common.utils.Page;
import com.distribution.dao.memberNode.model.MemberNode;
import com.distribution.dao.order.model.OrderMaster;
import com.distribution.service.NodeService;
import com.distribution.service.OrderService;

public class NodeServiceTest extends SpringServiceTest{
	private static Logger log = Logger.getLogger(NodeServiceTest.class);
	@Autowired
	private NodeService nodeService;
	@Autowired
	private OrderService orderService; 
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
		Map<String,String> map = nodeService.getSubNodeNumberAndSales(1);
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
	@Test
	public void insertMemberNodeBonusForGenerateData(){
		int nodeId = 571;
		int orderMemberId = 571;
		int[] memberIds = {551,493};
		String date = "2017-12-10";
		List<OrderMaster> list = orderService.listOrdersByMemberId(orderMemberId,date);
		nodeService.insertMemberNodeBonusTest(nodeId, list,memberIds);
	}
	@Test
	public void processTotalSalesResultTest() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("leftToalSales","34890000");
		map.put("rightToalSales","60012128");
		nodeService.processTotalSalesResult(map);
	}
	@Test
	public void listOperatorLeftAndRightSalesTest() {
		Page page = new Page();
		page.setPageNo(1);
		page.setPageSize(30);
		Map map = new HashMap();
	    //map.put("startDate", "2017-12-01");
		//map.put("endDate", "2017-12-09");
		map.put("scope", "all");
		page.setParameterMap(map);
		page = nodeService.listOperatorLeftAndRightSales(page);
		try {
			File file = new File("E:\\dev\\personal\\test12.xlsx");
			OutputStream out = new FileOutputStream(file);
			XSSFWorkbook wf = nodeService.exportExcel(page.getResult());
			wf.write(out);
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
