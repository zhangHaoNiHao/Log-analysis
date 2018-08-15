package com.utils;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.utils.dataclean1.isInteger;


/**
 * 在map阶段进行清洗，只要article和video的数据,同时将数据按规定格式处理完毕，在reduce阶段只进行传递
 */
public class MyMap extends Mapper<LongWritable, Text, LongWritable, Text> {
    private final SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
    private final SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String result = "";
        String[] split = value.toString().split(",");

        if (split.length >= 4) {
            if (split[3].contains("video") || split[3].contains("article")) {
                String[] split1 = split[3].split("/");
                if (null != split1[3]) {
                    if (split1[3].equals("article") || split1[3].equals("video")) {

                        if (split1.length >= 5) {


                            split1[4] = split1[4].substring(0, split1[4].length() - 1);

                            if (split1[4].contains("\\?")) {
                                split1[4] = split1[4].substring(0, split1[4].indexOf("?") - 1);
                            }
                            boolean flag = isInteger(split1[4]);
                            if (flag == true) {
                                //这些是清洗后有用的数据
                                String city = IPutils.getCityInfo(split[0]);
                                String inputdate = split[1].replace("[", "").replace("]", "");
                                String[] split2 = inputdate.split("/");
                                String day = split2[0];
                                try {
                                    Date parse = inputFormat.parse(inputdate);
                                    String outputDate = outputFormat.format(parse);

                                    result += city + "\t" + day + "\t" + outputDate + "\t" + split1[3] + "\t" + split1[4] + "\t" + split[2];

                                    context.write(key, new Text(result));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                    }
                }
            }

        }
    }
}
