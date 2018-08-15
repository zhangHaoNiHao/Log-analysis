package com.dao;

import com.entity.TypeNum;
import com.utils.HiveJDBCUtils;
import com.utils.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImplDataDao implements DataDao {

    /**
     * 查找文章数量前十
     * @return
     * @throws SQLException
     */
    public List<TypeNum> artTop(String table) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select id,sum from "+table+"_article";
        List<TypeNum> datas = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum("article",id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 查找视频数量前十
     * @return
     * @throws SQLException
     */
    public List<TypeNum> vidTop(String table) throws Exception {
        System.out.println("video  "+table);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select id,sum from "+table+"_video";
        List<TypeNum> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum("video",id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 流量前10的视频课程
     * @return
     * @throws Exception
     */
    @Override
    public List<TypeNum> courseTop(String table) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select id,sum from "+table+"_vid_tra";
        List<TypeNum> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum("video",id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 流量前10的文章课程
     * @return
     * @throws SQLException
     */
    @Override
    public List<TypeNum> artcourseTop(String table) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select id,sum from "+table+"_art_tra";
        List<TypeNum> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum("video",id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 查询所有的视频城市
     * @return
     * @throws SQLException
     */
    public List<String> city() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select * from city";
        List<String> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String city = rs.getString("city");
                System.out.println("城市"+city);
                datas.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 查询所有的文章城市
     * @return
     * @throws SQLException
     */
    public List<String> cityart() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        String sql = "select * from cityart";
        List<String> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String city = rs.getString("city");
                System.out.println("城市"+city);
                datas.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 某个城市的视频课程前10
     * @param city
     * @return
     * @throws SQLException
     */
    public List<TypeNum> city_video(String city) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = JDBCUtil.getConnection();
        String sql = "select city,id,sum from city_video where city=? order by sum desc limit 5";
        List<TypeNum> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("city");
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum(type,id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 城市前5的文章课程
     * @param city
     * @return
     * @throws SQLException
     */
    public List<TypeNum> city_article(String city) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = JDBCUtil.getConnection();
        String sql = "select city,id,sum from city_article where city=? order by sum desc limit 5";
        List<TypeNum> datas = new ArrayList<>();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,city);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("city");
                String id = rs.getString("id");
                int sum = rs.getInt("sum");
                TypeNum tn = new TypeNum(type,id,sum);
                tn.toString();
                datas.add(tn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HiveJDBCUtils.close(conn,pstmt,rs);
        return datas;
    }

    /**
     * 创建hive表，并且将数据导入表中
     * @param filename
     * @return
     * @throws SQLException
     */
    public int createTable(String filename) throws SQLException{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        System.out.println(filename.split("\\.")[0]);
        String[] words = filename.split("\\.");
        String table = words[0];
        System.out.println(filename+"  "+table);
        String sql = "drop table if exists "+table;
        pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
        String sql1 = "create table "+table+" (id string,type string,traffic int,date1 string,day string,city string) row format delimited fields terminated by ' ' ";
        System.out.println("创建第一次清洗的表");
        pstmt = conn.prepareStatement(sql1);
        pstmt.setString(1, table);
        int a = 0;
        pstmt.executeUpdate();

        String sql2 = "load data inpath '/test/"+filename+"' into table "+table;
        pstmt = conn.prepareStatement(sql2);
        pstmt.setString(1, filename);
        pstmt.setString(2, table);
        a = pstmt.executeUpdate();
        HiveJDBCUtils.close(conn,pstmt,rs);
        return a;
    }

    /**
     * 创建结果表文章受欢迎前十的记录
     * @return
     */
    public int createResult1(String table) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        //如果已存在，就删除
        String sql1 = "drop table if exists "+table+"_article";
        pstmt = conn.prepareStatement(sql1);
        pstmt.executeUpdate();

        String sql2 = "drop table if exists "+table+"_video";
        pstmt = conn.prepareStatement(sql2);
        pstmt.executeUpdate();
        //建表
        String sql3 = "create table " + table + "_article (id String,sum int) row format delimited fields terminated by ' '";
        pstmt = conn.prepareStatement(sql3);
        pstmt.executeUpdate();

        String sql4 = "create table " + table + "_video (id String,sum int) row format delimited fields terminated by ' '";
        pstmt = conn.prepareStatement(sql4);
        pstmt.executeUpdate();
        //导入数据
        String sql5 = "insert into table " + table + "_article select id,count(*) sum from "+table+" where type='article' group by id order by sum desc limit 10";
        pstmt = conn.prepareStatement(sql5);
        pstmt.executeUpdate();

        String sql6 = "insert into table " + table + "_video select id,count(*) sum from "+table+" where type='video' group by id order by sum desc limit 10";
        pstmt = conn.prepareStatement(sql6);
        int a =0;
        a = pstmt.executeUpdate();
        HiveJDBCUtils.close(conn,pstmt,rs);
        return a;
    }

    /**
     * 建立结果表2，并将找出流量使用前十的视频和文章
     * @param table
     * @return
     */
    public int createResult2(String table) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        conn = HiveJDBCUtils.getConnection();
        //如果已存在，就删除
        //10000_access_art_tra
        //10000_access_vid_tra

        String sql1 = "drop table if exists "+table+"_art_tra";
        pstmt = conn.prepareStatement(sql1);
        pstmt.executeUpdate();

        String sql2 = "drop table if exists "+table+"_vid_tra";
        pstmt = conn.prepareStatement(sql2);
        pstmt.executeUpdate();

        String sql3 = "create table " + table + "_art_tra (id String,sum int) row format delimited fields terminated by ' '";
        pstmt = conn.prepareStatement(sql3);
        pstmt.executeUpdate();

        String sql4 = "create table " + table + "_vid_tra (id String,sum int) row format delimited fields terminated by ' '";
        pstmt = conn.prepareStatement(sql4);
        pstmt.executeUpdate();

        //导入数据
        String sql5 = "insert into table " + table + "_art_tra select id,sum(traffic) sum from "+table+" where type='article' group by id order by sum desc limit 10";
        pstmt = conn.prepareStatement(sql5);
        pstmt.executeUpdate();

        String sql6 = "insert into table " + table + "_vid_tra select id,sum(traffic) sum from " + table + " where type='video' group by id order by sum desc limit 10";
        pstmt = conn.prepareStatement(sql6);
        int a =0;
        a = pstmt.executeUpdate();
        HiveJDBCUtils.close(conn,pstmt,rs);
        return a;
    }

}
