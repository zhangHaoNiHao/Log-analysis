package com.service;

import com.dao.DataDao;
import com.entity.TypeNum;
import com.entity.data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataDao dataDao;

    /**
     * 查找数量前10的文章
     * @return
     * @throws Exception
     */
    public List<TypeNum> artTop(String table) throws Exception {

        return dataDao.artTop(table);
    }

    /**
     * 查找数量前10的视频
     * @return
     * @throws Exception
     */
    public List<TypeNum> vidTop(String table) throws Exception {

        return dataDao.vidTop(table);
    }

    /**
     * 查找流量前十的视频课程
     * @return
     * @throws Exception
     */
    public List<TypeNum> courseTop(String table) throws Exception {

        return dataDao.courseTop(table);
    }

    /**
     * 查找流量前十的文章课程
     * @return
     * @throws Exception
     */
    public List<TypeNum> artcourseTop(String table) throws Exception {

        return dataDao.artcourseTop(table);
    }

    /**
     * 查询所有的视频城市名
     * @return
     * @throws Exception
     */
    public List<String> city() throws Exception {

        return dataDao.city();
    }

    /**
     * 查询所有的文章城市名
     * @return
     * @throws Exception
     */
    public List<String> cityart() throws Exception {

        return dataDao.cityart();
    }

    public List<TypeNum> city_video(String city) throws Exception {

        return dataDao.city_video(city);
    }
    public List<TypeNum> city_article(String city) throws Exception {

        return dataDao.city_article(city);
    }

    /**
     * 建hive表，并导入数据
     * @param filename
     * @return
     * @throws SQLException
     */
    public int createTable(String filename) throws SQLException {
        return dataDao.createTable(filename);
    }

    /**
     * 建立结果表
     * 查询最受欢迎的前10的文章和视频
     */
    public int createResult1(String table) throws Exception {
        return dataDao.createResult1(table);
    }

    /**
     * 建立结果表2
     * 查询流量前十的文章和视频
     * @param table
     * @return
     * @throws Exception
     */
    public int createResult2(String table) throws Exception {
        return dataDao.createResult2(table);
    }
}
