package com.wzy.controller.FileUpload;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类功能说明: @RestController表示该类下的方法会在自动做json格式的转化
 * 类修改者	创建日期2019/2/14
 * 修改说明
 * <p>Title: uploadContrller.java</p>
 *
 * @author Wuzhaoyu
 * @version V1.0
 **/
@RestController
public class uploadContrller {

    /**
     * 上传文件
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/fileUploadController",method = RequestMethod.POST)
    public Map<String,Object> fileUpload(MultipartFile fileName, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        map.put("flag",true);
        String realPath = session.getServletContext().getRealPath("/uploads");
        File temp = new File(realPath);
        if(!temp.exists()){
            //如果不存在，就新建一个路径
            temp.mkdir();
        }
        String filename = fileName.getOriginalFilename();
        //限制上传的文件必须是图片，通过后缀名的方式
        String suffix = filename.substring(filename.lastIndexOf(".") + 1);
        if(!suffix.matches("^(?i)[(PNG)|(GIF)|(JPG)|(JPEG)]+$")){
            map.put("flag",false);
            map.put("message","请上传图片！");
            return map;
        }
        //如果图片超过512M，返回false
        if(fileName.getSize()>512*1024*1024){
            map.put("flag",false);
            map.put("message","文件不支持>512KB!");
            return map;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        filename = filename.substring(0,filename.lastIndexOf("."))+"_"+format+filename.substring(filename.lastIndexOf("."));
        File f = new File(realPath+File.separator+filename);
        try {
            fileName.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("flag",false);
            map.put("message","上传失败，详细信息为："+e.getMessage());
            return map;
        }
        map.put("filename",filename);
        return map;
    }
}
