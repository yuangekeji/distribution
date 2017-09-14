package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.goods.model.Goods;
import com.distribution.service.AdmGoodsService;
import com.distribution.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/admGoods")
@Controller
public class AdmGoodsController extends BasicController{

    @Autowired
    private AdmGoodsService admGoodsService;
    @Autowired
    private CommonService commonService;

    /**
     * description 查询商品类型
     * @author Bright
     * */
    @RequestMapping("/findGoodsTypes")
    @ResponseBody
    public JsonMessage findGoodsTypes(){
        List<Dictionary> list = commonService.selectDictionary("goods_type");
        return successMsg(list);
    }

    /**
     * description 添加商品
     * @author Bright
     * */
    @RequestMapping("/insert")
    @ResponseBody
    public JsonMessage insertGoods(@RequestBody Goods goods){
        if(admGoodsService.insertGoods(goods)>0){
            return successMsg();
        }else{
            return failMsg();
        }
    }

    /**
     * description 商品列表查询
     * @author Bright
     * */
    @RequestMapping("/list")
    @ResponseBody
    public JsonMessage list(@RequestBody Page page){
        page = admGoodsService.findList(page);
        return successMsg(page);
    }
}
