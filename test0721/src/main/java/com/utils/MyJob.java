package com.utils;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MyJob {
    public static void main(String[] args) {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","hdfs://192.168.120.100:9000");
        Path outputDir = new Path("/tmp/output");
        FileSystem fs = null;
        try {
            fs = FileSystem.get(conf);
            if (fs.exists(outputDir)) {
                fs.delete(outputDir, true);
                System.out.println("删除成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Job job = null;
        try {
            job = Job.getInstance(conf);
            job.setJarByClass(MyJob.class);
            job.setMapperClass(MyMap.class);
            job.setReducerClass(MyReduce.class);

            job.setOutputKeyClass(LongWritable.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job,new Path("/tmp/000000_0"));
            FileOutputFormat.setOutputPath(job,new Path("/tmp/output"));

            job.waitForCompletion(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
