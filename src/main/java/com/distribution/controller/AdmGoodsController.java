package com.distribution.controller;

import com.distribution.common.constant.JsonMessage;
import com.distribution.common.controller.BasicController;
import com.distribution.common.utils.ImageUtil;
import com.distribution.common.utils.Page;
import com.distribution.common.utils.PropertiesUtil;
import com.distribution.dao.dictionary.model.Dictionary;
import com.distribution.dao.goods.model.Goods;
import com.distribution.service.AdmGoodsService;
import com.distribution.service.CommonService;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.UUID;

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

    /**
     * description 上传图片
     * @author Bright
     * */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public JsonMessage uploadImage(HttpServletRequest request){

        String fileType = "JPG,PNG,BMP,JPEG";
        String maxSize = "5";
        String filePath = PropertiesUtil.INSTANCE.getLocalFileRootDir() + File.separator;
        try {
            FileItem items = ((CommonsMultipartFile)((DefaultMultipartHttpServletRequest) request).getFile("image")).getFileItem();
            //文件名
            String fileName = items.getName();
            InputStream is = items.getInputStream();
            //检查文件后缀格式
            String fileEnd = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
            if(fileType!=null && !"".equals(fileType.trim())){
                boolean isRealType = false;
                String[] arrType = fileType.split(",");
                for (String str : arrType) {
                    if(fileEnd.equals(str.toLowerCase())){
                        isRealType = true;
                        break;
                    }
                }
                if(!isRealType){
                    //提示错误信息:文件格式不正确
                    System.out.println("文件格式不正确!");
                    return failMsg("文件格式不正确，请上传JPG,PNG,BMP,JPEG格式的图片");
                }
            }

            //创建文件唯一名称
            String uuid = UUID.randomUUID().toString();
            //真实上传路径
            StringBuffer sbRealPath = new StringBuffer();
            sbRealPath.append(filePath).append(uuid).append(".").append(fileEnd);


            //写入文件
            File file = new File(sbRealPath.toString());
            OutputStream os = new FileOutputStream(file);
            byte[] bytes = new byte[20*1024];//创建一个缓冲区
            int b;//记录读数据时的末尾位置
            while((b = is.read(bytes, 0, bytes.length)) != -1){//如果未读到文件末尾
                os.write(bytes, 0, b);//将缓冲区内的数据写入目标文件
                os.flush();//刷新缓冲区
            }
            os.close();
            is.close();
            return successMsg(sbRealPath.toString());
        }catch (Exception e) {
            //提示错误:比如文件大小
            e.printStackTrace();
            System.out.println("上传失败,文件大小不能超过"+maxSize+"M!");
            return failMsg("上传失败,文件大小不能超过"+maxSize+"M!");
        }
    }

    /**
     * description 修改上架下架
     * @author Bright
     * */
    @RequestMapping("/handle")
    @ResponseBody
    public JsonMessage handle(@RequestBody Goods goods){
        Integer it = admGoodsService.handle(goods);
        if(it>0){
            return successMsg();
        }else{
            return failMsg();
        }
    }

    /**
     * description 根据ID查询物品
     * @author Bright
     * */
    @RequestMapping("/getGoodsById")
    @ResponseBody
    public JsonMessage getGoodsById(Integer id){
        Goods goods = admGoodsService.getGoodsById(id);
        return successMsg(goods);
    }

    /**
     * description 显示图片
     * */
    @RequestMapping(value = "/showPic")
    public void showPic(String picUrl, HttpServletResponse response) throws IOException {
        ImageUtil.showPic(picUrl,response,"contentPic");
    }

}
