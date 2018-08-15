package com.controller;

import com.service.DataService;
import com.utils.upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.hadoop.fs.Path;



@Controller
public class FileController {
    @Autowired //自动注入
    private DataService dataService;

    @RequestMapping(value="/upload")
    public String upload(String path) throws Exception {
        //上传文件
        boolean b = upload.upload(path);
        if(!b){
            System.out.println("上传失败");
            return null;
        }
        System.out.println("上传成功");
        Path p = new Path(path);
        String filename = p.getName();
        int a = 0;
        dataService.createTable(filename);
        if(a > 0){
            System.out.println("创建成功");
        }
        return "index";
    }

    /**
     * 建立前10受欢迎的文章和视频
     * @param path
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/clean")
    public String createResult1 (String path,String type) throws Exception {
        //选择第一次清洗后的文件，主要是获取文件名，然后得到表名
        type = "article";
        Path p = new Path(path);
        String filename = p.getName();//文件名
        String table = filename.split("\\.")[0];
        System.out.println("清洗表 "+table);
        int a = 0,b=0;
        a = dataService.createResult1(table);
        b = dataService.createResult2(table);
        if(a>0){
            System.out.println("清洗成功");
        }else{
            System.out.println("清洗失败");
        }
        return "index";
    }

}
