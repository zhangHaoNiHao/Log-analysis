package com.utils;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;


import java.io.File;
import java.lang.reflect.Method;

import static org.lionsoul.ip2region.Util.isIpAddress;

public class IPutils {
    public static String getCityInfo(String ip) {

        //db
        String dbPath = IPutils.class.getResource("ip2region.db").getPath();
        //String dbPath=IPutils.class.getClassLoader().getResource("ip2region.db").getPath();
        System.out.println(dbPath);
        File file = new File(dbPath);
        if (file.exists() == false) {
            System.out.println("Error: Invalid ip2region.db file");
        }

        //查询算法
        int algorithm = DbSearcher.BTREE_ALGORITHM; //B-tree
        //DbSearcher.BINARY_ALGORITHM //Binary
        //DbSearcher.MEMORY_ALGORITYM //Memory
        try {
            DbConfig config = new DbConfig();
            DbSearcher searcher = new DbSearcher(config, dbPath);

            //define the method
            Method method = null;
            switch (algorithm) {
                case DbSearcher.BTREE_ALGORITHM:
                    method = searcher.getClass().getMethod("btreeSearch", String.class);
                    break;
                case DbSearcher.BINARY_ALGORITHM:
                    method = searcher.getClass().getMethod("binarySearch", String.class);
                    break;
                case DbSearcher.MEMORY_ALGORITYM:
                    method = searcher.getClass().getMethod("memorySearch", String.class);
                    break;
            }

            DataBlock dataBlock = null;
            if (isIpAddress(ip) == false) {
                System.out.println("Error: Invalid ip address");
            }

            dataBlock = (DataBlock) method.invoke(searcher, ip);

            //return dataBlock.getRegion();
            dataBlock = (DataBlock) method.invoke(searcher, ip);
            String[] split = dataBlock.getRegion().split("\\|");

            return split[3];

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void main(String[] args) throws Exception {
        System.err.println(getCityInfo("59.41.64.207"));
    }
}
