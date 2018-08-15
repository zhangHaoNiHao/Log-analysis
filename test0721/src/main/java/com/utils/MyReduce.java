package com.utils;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyReduce extends Reducer<LongWritable,Text,LongWritable,Text> {
    private static int i=0;
    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text value:values
             ) {
            i++;
            context.write(new LongWritable(i),new Text(value));
        }
    }
}
