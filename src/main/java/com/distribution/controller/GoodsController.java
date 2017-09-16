package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.Page;
import com.distribution.common.utils.PropertiesUtil;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.goods.mapper.more.MoreGoodsMapper;
import com.distribution.dao.goods.model.Goods;
import com.distribution.service.AdmGoodsService;
import com.distribution.service.CommonService;
import com.distribution.service.GoodsService;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BasicController{

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CommonService commonService;
    /**
     * description 查询商品详情
     * @author wyn
     * */
    @RequestMapping(value = "/details/{id}")
    @ResponseBody
    public JsonMessage goodDetails(@PathVariable Integer id){
        Goods goods = goodsService.goodDetails(id);

        Map result= new HashMap();
        result.put("goods",goods);

        return successMsg(result);
    }

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

}
