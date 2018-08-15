package com.dao;

import com.entity.TypeNum;

import java.sql.SQLException;
import java.util.List;

public interface DataDao {

    List<TypeNum> artTop(String table) throws Exception;
    List<TypeNum> vidTop(String table) throws Exception;
    List<TypeNum> courseTop(String table) throws Exception;
    List<TypeNum> artcourseTop(String table) throws SQLException;
    List<TypeNum> city_video(String city) throws SQLException;
    List<TypeNum> city_article(String city) throws SQLException;
    List<String> city() throws SQLException;
    List<String> cityart() throws SQLException;
    //创建表，并导入数据
    int createTable(String filename) throws SQLException;
    //创建结果表1，受欢迎前10的文章和视频
    int createResult1(String table) throws Exception;
    int createResult2(String table) throws SQLException;
}
