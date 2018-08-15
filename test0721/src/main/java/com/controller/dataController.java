package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.entity.TypeNum;
import com.service.DataService;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
public class dataController  {

    /**
     * 将所有的图表显示在一个页面上，当选择文件后，直接显示所有的图表
     */
    @Autowired //自动注入
    private DataService dataService;

    @RequestMapping(value="/index")
    public String index(){
        int a=0;
        return "index";
    }

    //点击次数前10的文章 top10
    @RequestMapping(value="/article")
    @ResponseBody
    public List<TypeNum> article(String path) throws Exception {
        String table = (new Path(path)).getName().split("\\.")[0];
        System.out.println("article "+table );
        List<TypeNum> list = dataService.artTop(table);
        System.out.println("test "+ list.toString());
        return list;
    }

    //点击次数前10的视频
    @RequestMapping(value="/video")
    @ResponseBody
    public List<TypeNum> video(String path) throws Exception {
        String table = (new Path(path)).getName().split("\\.")[0];
        System.out.println("video  "+table);
        List<TypeNum> list = dataService.vidTop(table);
        return list;
    }


    //流量前10的视频课程
    @RequestMapping(value="/trafficvideo")
    @ResponseBody
    public List<TypeNum> course(String path) throws Exception {
        System.out.println("tra "+path);
        String table = (new Path(path)).getName().split("\\.")[0];
        List<TypeNum> list = dataService.courseTop(table);
        return list;
    }

    //流量前10的视频文章
    @RequestMapping(value="/trafficarticle")
    @ResponseBody
    public List<TypeNum> artcourse(String path) throws Exception {
        Path p = new Path(path);
        String table = p.getName().split("\\.")[0];
        List<TypeNum> list = dataService.courseTop(table);
        return list;
    }

    //查询所有的视频城市
    @RequestMapping(value="/city")
    @ResponseBody
    public List<String> city() throws Exception {
        List<String> list = dataService.city();
        return list;
    }

    //查询所有的文章城市
    @RequestMapping(value="/cityart")
    @ResponseBody
    public List<String> cityart() throws Exception {
        List<String> list = dataService.cityart();
        return list;
    }

    //根据城市查询该城市的前5条
    @RequestMapping(value="/city_video")
    @ResponseBody
    public List<TypeNum> citytop(String city) throws Exception {
        System.out.println(city);
        List<TypeNum> list = dataService.city_video(city);
        return list;
    }

    //根据城市查询该城市的前5条
    @RequestMapping(value="/city_article")
    @ResponseBody
    public List<TypeNum> city_article(String city) throws Exception {
        System.out.println(city);
        List<TypeNum> list = dataService.city_article(city);
        return list;
    }
}
