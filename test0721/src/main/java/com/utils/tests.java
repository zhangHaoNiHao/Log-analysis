package com.utils;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import org.apache.hadoop.fs.Path;
import org.junit.Test;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;
import org.lionsoul.ip2region.Util;

import java.io.*;
import java.net.InetAddress;
import java.util.regex.Pattern;

public class tests {
    /*
    public static void main(String[] args) throws Exception {
        read2();
    }
    */
    public static void read2(String input) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));
        Path p = new Path(input);
        String filename = p.getName();
        PrintWriter pw = new PrintWriter("f:/test/"+filename);
        String line = bf.readLine();
        int i = 1;
        while(line != null)
        {
            String[] words = line.split(" ");

            if((words[11].contains("article")) || (words[11].contains("video")))
            {
                String[] ws = words[11].split("/");
                if(ws[3].equals("article") || ws[3].equals("video"))
                {
                    String type = ws[3];

                    if (ws[4].contains("?")) {
                        ws[4] = ws[4].split("\\?")[0];
                    } else if (ws[4].contains("/")) {
                        ws[4] = ws[4].split("/")[0];
                    }else {
                        ws[4]  = ws[4].substring(0,ws[4].length()-1);
                        if( !isInteger(ws[4])){
                            line = bf.readLine();
                            continue;
                        }
                    }
                    //ip
                    String city = ip_city(words[0]);

                    String[] time = words[3].split(":");
                    String hour = time[1];
                    String min = time[2];
                    String sec = time[3];
                    String date = "2016-11-10,"+hour+":"+min+":"+sec;
                    String day = "10";

                    //流量
                    String traffic = words[9];
                    System.out.println(i+" "+city +" " + date + " "+day +" "+ traffic + " "+type+" "+ws[4]);

                    //将数据导入到文件中 id,type,traffic,date,day,city

                    pw.println(ws[4]+" "+type+" "+traffic+" "+date+" "+day+" "+city);

                    i++;
                }
            }
            line = bf.readLine();
        }
        pw.close();
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

/*
    //根据ip获得城市
    public static String ip_city(String a) throws Exception {
        File database = new File("F:\\GeoLite2-City_20180703\\GeoLite2-City_20180703\\GeoLite2-City.mmdb");
        DatabaseReader reader = new DatabaseReader.Builder(database).build();
        InetAddress ipAddress = InetAddress.getByName(a);
        CityResponse response = reader.city(ipAddress);

        City city = response.getCity();
        return city.getName();
    }
*/

    public static String ip_city(String a) throws Exception {
        DbConfig conf = new DbConfig();
            String dbFile = "f:/ip2region.db";
            DbSearcher searcher = new DbSearcher(conf, dbFile);
            DataBlock block = searcher.btreeSearch(a);
            String region = block.getRegion();
            region = region.replaceAll("\\d+\\|", "");
            String[] words = region.split("\\|");

            if(words.length>=4){
                return words[2];
            }else{
                return words[0];
            }
    }

    @Test
    public void teste() throws Exception {

        DbConfig conf = new DbConfig();
        String dbFile = "f:/ip2region.db";
        DbSearcher searcher = new DbSearcher(conf, dbFile);
        DataBlock block = searcher.btreeSearch("98.25.241.43");
        String region = block.getRegion();
        region = region.replaceAll("\\d+\\|", "");
        String[] words = region.split("\\|");
        System.out.println(region);
        //2875 2360
        if(words.length>=4){
            System.out.println(words[2]);
            //return words[2];
        }else{
            System.out.println(words[0]);;
        }

    }
}
