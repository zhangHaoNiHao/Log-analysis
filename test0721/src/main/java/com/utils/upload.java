package com.utils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.net.URI;

/**
 * 文件上传
 * @author Mr_Zhang
 *
 */
public class upload {
	public static boolean upload(String p) throws Exception {

		 Configuration conf = new Configuration();
		 FileSystem fileSystem ;

		 //第一次清洗数据，从记录中将数据提取出来
		 tests.read2(p); //将提取出的数据放人f:/test/文件名

		 String path = "hdfs://192.168.120.100:9000";
		 fileSystem=FileSystem.get(new URI(path),conf,"root");

		 String filename = new Path(p).getName();


		 Path localpath = new Path("f:/test/"+filename);

		 Path hdfspath = new Path("/test");
		 fileSystem.copyFromLocalFile(localpath,hdfspath);

		if(fileSystem.exists(new Path("/test/"+filename)))
			return true;
		else
			return false;
	}
	
}
